package tworeal.Animalcenter.domain.account.entity;

import lombok.Getter;

public enum UserStatus {
    /**
     * 계정 상태값
     */
    ACCOUNT_ACTIVE("활동중"),
    ACCOUNT_SLEEP("휴면 계정"),
    ACCOUNT_QUIT("탈퇴 계정");


    @Getter
    private String status;

    /**
     * 객체 생성을 위한 값을 받아 생성된 객체에 넣어주기 위함
     * 파라미터 + 필드 추가를 할 수 있다
      * @param status
     */
    UserStatus(String status) {
        this.status = status;
    }
}