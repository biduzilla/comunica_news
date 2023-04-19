package com.comunica.news.service.impl;

import com.comunica.news.dto.PostDto;
import com.comunica.news.dto.PostEnviadoDto;
import com.comunica.news.exception.PostNaoEncontrado;
import com.comunica.news.exception.PostNaoTePertence;
import com.comunica.news.exception.PostsNaoCadastrados;
import com.comunica.news.exception.UserNaoEncontrado;
import com.comunica.news.models.Post;
import com.comunica.news.models.Usuario;
import com.comunica.news.repository.PostRepository;
import com.comunica.news.repository.UserRepository;
import com.comunica.news.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final UsuarioServiceAuthImpl usuarioServiceAuth;

    @Override
    public void criarPost(PostDto postDto, String token) {
        Usuario usuario = usuarioServiceAuth.searchUserByToken(token);

        Post post = Post.builder()
                .id(UUID.randomUUID().toString())
                .autor(usuario.getNome())
                .titulo(postDto.getTitulo())
                .descricao(postDto.getDescricao())
                .img(postDto.getImg())
                .data(LocalDate.now())
                .usuario(usuario)
                .build();
        postRepository.save(post);
    }

    @Override
    public List<PostEnviadoDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();

        return postsToPostsDto(posts);
    }

    private static List<PostEnviadoDto> postsToPostsDto(List<Post> posts) {
        List<PostEnviadoDto> postsDto = posts.stream().map(post -> PostEnviadoDto.builder()
                .id(post.getId())
                .autor(post.getAutor())
                .prazo(post.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .titulo(post.getTitulo())
                .descricao(post.getDescricao())
                .img(post.getImg())
                .build()).collect(Collectors.toList());

        if (postsDto.isEmpty()) {
            throw new PostsNaoCadastrados();
        }

        return postsDto;
    }

    @Override
    public List<PostEnviadoDto> getAllMyPosts(String idUser) {
        List<Post> posts = postRepository.findAllByUsuarioId(idUser).orElseThrow(UserNaoEncontrado::new);

        return postsToPostsDto(posts);
    }

    @Override
    public PostEnviadoDto getPostById(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNaoEncontrado::new);

        return getPostEnviadoDto(post);
    }

    @Override
    public PostEnviadoDto getMyPostById(String postId, String userId) {
        Usuario usuario = userRepository.findById(userId).orElseThrow(UserNaoEncontrado::new);

        if (verificaDonoPost(usuario.getId(), postId)) {
            throw new PostNaoTePertence();
        }

        Post post = postRepository.findById(postId).orElseThrow(PostNaoEncontrado::new);

        return getPostEnviadoDto(post);
    }

    @Override
    public void updatePost(PostDto postDto, String postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNaoEncontrado::new);

        post.setTitulo(postDto.getTitulo());
        post.setImg(postDto.getImg());
        post.setDescricao(postDto.getDescricao());

        postRepository.save(post);
    }

    @Override
    public void deletePost(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNaoEncontrado::new);
        postRepository.delete(post);
    }

    private static PostEnviadoDto getPostEnviadoDto(Post post) {
        return PostEnviadoDto.builder()
                .id(post.getId())
                .autor(post.getAutor())
                .titulo(post.getTitulo())
                .descricao(post.getDescricao())
                .img(post.getImg())
                .prazo(post.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .build();
    }


    public boolean verificaDonoPost(String idUser, String idPost) {
        List<String> postsIds = postRepository.findAllByUsuarioId(idUser).orElseThrow(UserNaoEncontrado::new)
                .stream()
                .map(
                        Post::getId
                )
                .collect(Collectors.toList());

        return !postsIds.contains(idPost);
    }
}
