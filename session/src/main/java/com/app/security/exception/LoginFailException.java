package com.app.security.exception;

import com.app.security.constant.ErrorCode;

public class LoginFailException  extends RuntimeException{
    public LoginFailException(){
        super(ErrorCode.LOGIN_FAILED.getMessage());
    }
    public LoginFailException(String message){
        super(message);
    }
}
