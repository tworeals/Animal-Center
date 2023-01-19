package tworeal.Animalcenter.domain.account.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Account {

    @Id
    private long id;

    @Email
    private String email;

    @NotNull
    private Name nickname;

    @NotNull
    private String password;

    @NotNull
    private Phone phone;

    private UserState state;

    /**
     * 불변 밸류의 명료화
     */
    class Name {
        private String nickname;

        public Name (String nickname) {
            this.nickname = nickname;
        }
    }

    /**
     * 불변 밸류의 명료화
     */
    class Phone {
        private String phone;

        public Phone (String phone) {
            this.phone = phone;
        }
    }


}
