package tworeal.Animalcenter.domain.account.dto;

import lombok.Getter;
import lombok.Setter;
import tworeal.Animalcenter.domain.account.entity.Account;
import tworeal.Animalcenter.domain.account.entity.UserStatus;
import tworeal.Animalcenter.domain.account.value.Name;
import tworeal.Animalcenter.domain.account.value.Password;
import tworeal.Animalcenter.domain.account.value.Photo;

@Getter @Setter
public class AccountPatchReqDto {

    private String password;
    private String nickname;
    private String profile;
    private UserStatus accountStatus;

    /**
     * 밸류지정방식
     */
    public Account toEntity() {
        Account account = new Account().builder()
                .password(new Password(this.password))
                .nickname(new Name(this.nickname))
                .profile(new Photo(this.profile))
                .accountStatus(this.accountStatus)
                .build();
        return account;
    }
}