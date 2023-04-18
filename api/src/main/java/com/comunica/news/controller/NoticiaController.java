package com.comunica.news.controller;

import com.comunica.news.dto.NoticiaDto;
import com.comunica.news.service.NoticiaService;
import com.comunica.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("noticias/")
@RequiredArgsConstructor
public class NoticiaController {

    private final NoticiaService noticiaService;

    @PostMapping("/salvarNoticia")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarNoticia(@RequestBody @Valid NoticiaDto noticia, @RequestHeader("Authorization") String token) {
        noticiaService.criarNoticia(noticia, token);
    }
}
