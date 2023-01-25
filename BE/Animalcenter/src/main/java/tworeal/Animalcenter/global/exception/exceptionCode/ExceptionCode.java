package tworeal.Animalcenter.global.exception.exceptionCode;

import lombok.Getter;

public enum ExceptionCode {
    /**
     * 필요한 에러를 형식에 맞게 입력해주세요
     */
    ACCOUNT_NOT_FOUND(400, "등록된 계정이 존재하지 않습니다."),
    NON_ACCESS_MODIFY(401, "수정권한이 없습니다.");

    @Getter
    private int status;
    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
