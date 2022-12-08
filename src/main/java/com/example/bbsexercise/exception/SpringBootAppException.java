package com.example.bbsexercise.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpringBootAppException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;

    @Override
    public String toString() {
        if (message == null) errorCode.getMessage();
        return errorCode.getMessage() + " " + message;
    }
}
