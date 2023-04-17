package com.comunica.news.exception;

public class UserNaoEncontrado extends RuntimeException {
    public UserNaoEncontrado() {
        super("Usuario não encontrado");
    }
}
