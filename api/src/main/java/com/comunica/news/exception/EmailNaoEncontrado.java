package com.comunica.news.exception;

public class EmailNaoEncontrado extends RuntimeException {
    public EmailNaoEncontrado() {
        super("Email não encontrado");
    }
}
