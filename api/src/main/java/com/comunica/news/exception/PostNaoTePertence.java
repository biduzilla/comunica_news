package com.comunica.news.exception;

public class PostNaoTePertence extends RuntimeException {
    public PostNaoTePertence() {
        super("Você não é o dono desse post");
    }
}
