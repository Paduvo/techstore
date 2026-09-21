package com.gustavo.techstore.exception;

public class EmailJaCadastradoException extends RuntimeException {

    public EmailJaCadastradoException(String email) {
        super("O email já está cadastrado: " + email);
    }
}