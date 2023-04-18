package com.comunica.news.service;

import com.comunica.news.dto.NoticiaDto;

public interface NoticiaService {
    void criarNoticia(NoticiaDto noticiaDto, String token);
}
