package com.app.security.exception;

import com.app.security.constant.ErrorCode;

public class CustomAuthenticationException extends RuntimeException{
    public CustomAuthenticationException(){
        super(ErrorCode.AUTHENTICATION_FAILED.getMessage());
    }
    public CustomAuthenticationException(String message){
        super(message);
    }
}
