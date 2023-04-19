package com.comunica.news.service.impl;

import com.comunica.news.dto.ComentarioDto;
import com.comunica.news.exception.ComentariosNaoPublicados;
import com.comunica.news.exception.PostNaoEncontrado;
import com.comunica.news.exception.UserNaoEncontrado;
import com.comunica.news.models.Comentario;
import com.comunica.news.models.Post;
import com.comunica.news.models.Usuario;
import com.comunica.news.repository.ComentarioRepository;
import com.comunica.news.repository.PostRepository;
import com.comunica.news.repository.UserRepository;
import com.comunica.news.service.ComentarioService;
import com.comunica.news.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComentarioServiceImpl implements ComentarioService {

    private final PostRepository postRepository;
    private final ComentarioRepository comentarioRepository;
    private final UserRepository userRepository;

    @Override
    public void publicarComentario(ComentarioDto comentarioDto, String idPost, String idUser) {
        Post post = postRepository.findById(idPost).orElseThrow(PostNaoEncontrado::new);
        Usuario usuario = userRepository.findById(idUser).orElseThrow(UserNaoEncontrado::new);

        Comentario comentario = Comentario.builder()
                .id(UUID.randomUUID().toString())
                .comentario(comentarioDto.getComentario())
                .nome(usuario.getNome())
                .post(post)
                .build();
    }

    @Override
    public List<ComentarioDto> getlAllComentariosDoPost(String idPost) {
        List<Comentario> comentarios = comentarioRepository.findAllByPostId(idPost);

        return comentariosToDto(comentarios);

    }

    private List<ComentarioDto> comentariosToDto(List<Comentario> comentarios) {
        List<ComentarioDto> comentarioDtos = comentarios.stream().map(comentario -> ComentarioDto.builder()
                .id(comentario.getId())
                .comentario(comentario.getComentario())
                .build()).collect(Collectors.toList());

        if (comentarioDtos.isEmpty()) {
            throw new ComentariosNaoPublicados();
        }

        return comentarioDtos;
    }

    @Override
    public void deletarComentario(ComentarioDto comentarioDto, String idPost) {
        Post post = postRepository.findById(idPost).orElseThrow(PostNaoEncontrado::new);
        Comentario comentario = comentarioRepository.findById(comentarioDto.getId()).orElseThrow(ComentariosNaoPublicados::new);

        post.getComentarios().remove(comentario);

        postRepository.save(post);
        comentarioRepository.delete(comentario);
    }
}
