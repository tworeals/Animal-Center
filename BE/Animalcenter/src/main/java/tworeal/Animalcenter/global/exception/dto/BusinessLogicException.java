package tworeal.Animalcenter.global.exception.dto;

import lombok.Getter;
import tworeal.Animalcenter.global.exception.exceptionCode.ExceptionCode;

public class BusinessLogicException extends RuntimeException {

    @Getter
    private ExceptionCode exceptionCode;

    public BusinessLogicException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
