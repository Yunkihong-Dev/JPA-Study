package com.jpa.basic.exception;

import lombok.NoArgsConstructor;

public class NoProductException extends RuntimeException{
    public NoProductException(String message){
        super(message);
    }
}
