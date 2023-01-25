package tworeal.Animalcenter.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tworeal.Animalcenter.domain.account.entity.Account;
import tworeal.Animalcenter.domain.account.repository.AccountRepository;
import tworeal.Animalcenter.global.exception.dto.BusinessLogicException;
import tworeal.Animalcenter.global.exception.exceptionCode.ExceptionCode;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;


    /**
     * 예시 코드
     * accountId는 DB 연동 후 조회가 가능합니다.
     * @param account : 객체명
     * @return : 객체명 (식별자 반환을 위함)
     */
    @Transactional
    public Account createAccount(Account account) {
        account.defaultProfile();
        return accountRepository.save(account);
    }


    /**
     * 들어오는 값에 따라, 다음의 Patch 요청을 개별적으로 관리 가능합니다. (null이 아닐경우 수정되는 방식 미적용)
     * 1. 회원 정보 변경 가능
     * 2. 회원 상태 변경 가능
     * 비즈니스 로직은 Account Entity 내에서 처리
     * @param accountId 추후 JWT에서 추출 예정
     */
    @Transactional
    public void modifyMember(Account account, Long accountId) {
        Account existAccount = new Account().verifyAccount(accountRepository.findById(accountId));
        Optional.ofNullable(account.getPassword()).ifPresent(existAccount::modifyPassword);
        Optional.ofNullable(account.getNickname()).ifPresent(existAccount::modifyNickname);
        Optional.ofNullable(account.getProfile()).ifPresent(existAccount::modifyProfile);  // 현재 profile의 경우 단순 URI상태. 추후 파일로 변경 예정
        Optional.ofNullable(account.getAccountStatus()).ifPresent(existAccount::modifyAccountStatus);

        accountRepository.save(existAccount);
    }


    /**
     * Account 완전 삭제시 사용
     */
    @Transactional
    public void removeAccount (Long accountId) {
        Account verifyAccount = new Account().verifyAccount(accountRepository.findById(accountId));
        accountRepository.delete(verifyAccount);
    }

    /**
     * Account Entity클래스 내 구현한 회원탈퇴 메소드(withdrawAccount 호출)
     */
    @Transactional
    public void withdrawAccount (Long accountId) {
        Account verifyAccount = new Account().verifyAccount(accountRepository.findById(accountId));
        verifyAccount.withdrawAccount();
        accountRepository.save(verifyAccount);
    }


    /**
     * 현재는 Controller에서 String 반환중
     * @param accountId  : 추후 시큐리티 구현 후 적용될 예정
     * @return : 1명의 member 정보를 반환 => 맞춰서 ResponseDto 제작 예정
     */
    public Account findAccount(Long accountId) {
        Account account = new Account().verifyAccount(accountRepository.findById(accountId));
        return account;
    }


    /**
     * 전체 회원 조회용 (Default는 10계정)
     * @param pageable : page, size, sort 등 사용 가능
     * @return Page 구조
     */
    public Page<Account> findAccounts (Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

}
