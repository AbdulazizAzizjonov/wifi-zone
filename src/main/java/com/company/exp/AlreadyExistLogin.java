package com.company.exp;

public class AlreadyExistLogin extends RuntimeException {
    public AlreadyExistLogin(String massage) {
        super(massage);
    }
}
