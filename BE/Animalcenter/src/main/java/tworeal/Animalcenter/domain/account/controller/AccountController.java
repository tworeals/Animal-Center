package tworeal.Animalcenter.domain.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tworeal.Animalcenter.domain.account.dto.AccountPatchReqDto;
import tworeal.Animalcenter.domain.account.dto.AccountPostReqDto;
import tworeal.Animalcenter.domain.account.dto.AccountResDto;
import tworeal.Animalcenter.domain.account.entity.Account;
import tworeal.Animalcenter.domain.account.service.AccountService;
import tworeal.Animalcenter.global.dto.PageInfoDto;
import tworeal.Animalcenter.global.dto.SingleResDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    /**
     * Post 요청
     * @return "data" : "String"
     */
    @PostMapping
    public ResponseEntity<SingleResDto<Long>> postAccount (@RequestBody AccountPostReqDto accountPostReqDto) {
        Account savedAccount = accountService.createAccount(accountPostReqDto.toEntity());

        return new ResponseEntity<>(new SingleResDto<>(savedAccount.getAccountId()), HttpStatus.CREATED);
    }


    /**
     * Patch 요청
     * @return "data" : "String"
     * @param accountPatchReqDto : 요청 Body
     * @param accountId : 시큐리티 구성 전 임시 Variable
     * @return void
     */
    @PatchMapping("/edit/{account_id}")
    public ResponseEntity<SingleResDto<String>> patchAccount (@RequestBody AccountPatchReqDto accountPatchReqDto,
                                                             @PathVariable("account_id") Long accountId) {
        accountService.modifyMember(accountPatchReqDto.toEntity(), accountId);
        return new ResponseEntity<>(new SingleResDto<>("Success Patch"), HttpStatus.OK);
    }


    /**
     * Delete 요청
     * 애너테이션은 delete 요청을 받을 것이므로, DeleteMapping으로 받음
     * 상태변환을 할 예정이므로, HttpStatus는 NO_CONTENT가 아닌 OK로 함
     * 회원탈퇴(withdraw) 후 성공 메세지 반환
     * @return "data" : "성공 메세지"
     */
    @DeleteMapping("/remove/{account_id}")
    public ResponseEntity<SingleResDto<String>> withdrawAccount (@PathVariable("account_id") Long accountId) {
        accountService.withdrawAccount(accountId);

        return new ResponseEntity<>(new SingleResDto<>("Success Withdraw"), HttpStatus.OK);
    }


    /**
     * 회원 삭제 메소드
     * @return 삭제 성공 메세지
     */
    @DeleteMapping("/delete/{account_id}")
    public ResponseEntity<SingleResDto<String>> deleteAccount (@PathVariable("account_id") Long accountId) {
        accountService.removeAccount(accountId);

        return new ResponseEntity<>(new SingleResDto<>("Success Delete"), HttpStatus.OK);
    }



    /**
     * 단일 Get 요청
     * @return "data" : "단일 객체에 대한 응답정보"
     */
    @GetMapping("/find/{account_id}")
    public ResponseEntity<SingleResDto<AccountResDto>> getAccount (@PathVariable("account_id") Long accountId) {
        Account account = accountService.findAccount(accountId);
        AccountResDto response = new AccountResDto(account);

        return new ResponseEntity<>(new SingleResDto<>(response), HttpStatus.OK);
    }


    /**
     * Page Get 요청
     * @return "data" : "String"
     */
    @GetMapping("/find-all")
    public ResponseEntity<PageInfoDto<AccountResDto>> getMembers (Pageable pageable) {
        Page<Account> page = accountService.findAccounts(pageable);
        Page<AccountResDto> response = page.map(AccountResDto::new);

        return new ResponseEntity<>(new PageInfoDto<>(response), HttpStatus.OK);
    }


}
