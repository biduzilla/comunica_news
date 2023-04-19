package com.comunica.news.exception;

public class PostNaoEncontrado extends RuntimeException {
    public PostNaoEncontrado() {
        super("Post não encontrado");
    }
}
