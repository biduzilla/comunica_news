package com.comunica.news.service;

import com.comunica.news.dto.ComentarioDto;
import com.comunica.news.dto.PostDto;
import com.comunica.news.dto.PostEnviadoDto;

import java.util.List;

public interface ComentarioService {
    void publicarComentario(ComentarioDto comentarioDto, String idPost, String idUser);
    List<ComentarioDto> getlAllComentariosDoPost(String idPost);
    void deletarComentario(ComentarioDto comentarioDto, String idPost);
}
