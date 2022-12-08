package com.example.bbsexercise.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "username is duplicated."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "username is NOT FOUND"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "password is BAD REQUEST");


    private HttpStatus httpStatus;
    private String message;

}
