package tworeal.Animalcenter.domain.account.dto;

import lombok.Getter;
import lombok.Setter;
import tworeal.Animalcenter.domain.account.entity.Account;

@Getter @Setter
public class AccountResDto {

    private Long accountId;

    private String email;

    private String nickname;

    private String profile;

    private String accountStatus;


    public AccountResDto(Account account) {
        this.accountId = account.getAccountId();
        this.email = account.getEmail().getEmail();
        this.nickname = account.getNickname().getName();
        this.profile = account.getProfile().getPhoto();
        this.accountStatus = account.getAccountStatus().getStatus();
    }
}


