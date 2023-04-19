package com.comunica.news.exception;

public class ComentariosNaoPublicados extends RuntimeException {
    public ComentariosNaoPublicados() {
        super("Nenhum comentário foi publicado");
    }
}
