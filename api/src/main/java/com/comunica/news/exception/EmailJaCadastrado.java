package com.comunica.news.exception;

public class EmailJaCadastrado extends RuntimeException {
    public EmailJaCadastrado() {
        super("Email já cadastrado");
    }
}
