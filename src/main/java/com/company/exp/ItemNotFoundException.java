package com.company.exp;

import lombok.ToString;

@ToString
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String massage) {
        super(massage);
    }
}
