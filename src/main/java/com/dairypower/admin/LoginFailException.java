package com.dairypower.admin;

public class LoginFailException extends Exception{   

    private static final long serialVersionUID = 1L;

    public LoginFailException(String message) {
        super(message);
    }        
}
