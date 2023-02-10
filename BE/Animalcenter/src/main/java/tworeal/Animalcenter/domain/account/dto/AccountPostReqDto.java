package tworeal.Animalcenter.domain.account.dto;

import lombok.Getter;
import lombok.Setter;
import tworeal.Animalcenter.domain.account.entity.Account;
import tworeal.Animalcenter.domain.account.entity.UserStatus;
import tworeal.Animalcenter.domain.account.value.Email;
import tworeal.Animalcenter.domain.account.value.Name;
import tworeal.Animalcenter.domain.account.value.Password;
import tworeal.Animalcenter.domain.account.value.Photo;

@Getter @Setter
public class AccountPostReqDto {

    private String email;

    private String password;

    private String nickname;

    private String profile;

    public Account toEntity() {
        Account account = new Account().builder()
                .email(new Email(this.email))
                .password(new Password(this.password))
                .nickname(new Name(this.nickname))
                .profile(new Photo(this.profile))
                .accountStatus(UserStatus.ACCOUNT_ACTIVE)
                .build();
        return account;
    }
}