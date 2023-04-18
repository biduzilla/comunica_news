package com.comunica.news.service.impl;

import com.comunica.news.dto.NoticiaDto;
import com.comunica.news.exception.UserNaoEncontrado;
import com.comunica.news.models.Noticia;
import com.comunica.news.models.Usuario;
import com.comunica.news.repository.NoticiaRepository;
import com.comunica.news.repository.UserRepository;
import com.comunica.news.service.NoticiaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoticiaServiceImpl implements NoticiaService {

    private final NoticiaRepository noticiaRepository;
    private final UserRepository userRepository;
    private final UsuarioServiceAuthImpl usuarioServiceAuth;

    @Override
    public void criarNoticia(NoticiaDto noticiaDto, String token) {
        Usuario usuario = usuarioServiceAuth.searchUserByToken(token);

        Noticia noticia = Noticia.builder()
                .id(UUID.randomUUID().toString())
                .autor(usuario.getNome())
                .titulo(noticiaDto.getTitulo())
                .descricao(noticiaDto.getDescricao())
                .img(noticiaDto.getImg())
                .data(LocalDate.now())
                .usuario(usuario)
                .build();
        noticiaRepository.save(noticia);
    }
}
