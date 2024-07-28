package com.jardefelicio.api.excepetions;

public class UserFoundException extends RuntimeException{
    public UserFoundException(){
        super("O usuário já está cadastrado");
    }
}
