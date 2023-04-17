package com.comunica.news.exception;

public class TokenInvalidoException extends RuntimeException{
    public TokenInvalidoException() {
        super("Token Inválido!");
    }
}
