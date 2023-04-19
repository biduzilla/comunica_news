package com.comunica.news.exception;

public class PostsNaoCadastrados extends RuntimeException {
    public PostsNaoCadastrados() {
        super("Nenhum post cadastrado");
    }
}
