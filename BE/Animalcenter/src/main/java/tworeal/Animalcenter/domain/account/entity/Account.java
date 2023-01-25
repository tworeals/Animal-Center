package tworeal.Animalcenter.domain.account.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tworeal.Animalcenter.domain.account.value.*;
import tworeal.Animalcenter.global.exception.dto.BusinessLogicException;
import tworeal.Animalcenter.global.exception.exceptionCode.ExceptionCode;

import javax.persistence.*;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Embedded
    private Email email;
    @Embedded
    private Name nickname;
    @Embedded
    private Password password;
    @Embedded
    private Photo profile;
    @Embedded
    private Phone phone;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)  // 컬럼설정 지정 안할시 오류 발생 => Unable to instantiate custom type: org.hibernate.type.EnumType 에러발생
    private UserStatus accountStatus;

    public void defaultProfile() {
        if(this.profile.getPhoto()==null)
            this.profile = new Photo("https://scontent-gmp1-1.xx.fbcdn.net/v/t1.18169-9/527016_499021583525593_732357164_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=CdbnqyyFWXkAX_obHCp&_nc_ht=scontent-gmp1-1.xx&oh=00_AfDBQSsLnCXMKoAPnGFrOeSBgvMp__vgjXLEqmtS6etfcw&oe=63F1DB6A");
    }

    public Account verifyAccount(Optional<Account> optMember) {
        return optMember.orElseThrow(()->new BusinessLogicException(ExceptionCode.ACCOUNT_NOT_FOUND));
    }

    public void modifyPassword(Password password){ this.password = password; }
    public void modifyNickname(Name nickname){
        this.nickname = nickname;
    }
    public void modifyProfile(Photo profile){
        this.profile = profile;
    }
    public void modifyAccountStatus(UserStatus status){
        this.accountStatus = status;
    }
    public void withdrawAccount(){ this.accountStatus = UserStatus.ACCOUNT_QUIT; }

    public void checkPassword(String password) {
        if(!this.password.getPassword().equals(password)) throw new BusinessLogicException(ExceptionCode.NON_ACCESS_MODIFY);
    }
}
