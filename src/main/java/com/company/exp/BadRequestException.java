package com.company.exp;

import lombok.ToString;

@ToString
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
