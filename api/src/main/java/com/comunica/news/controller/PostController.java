package com.comunica.news.controller;

import com.comunica.news.dto.PostDto;
import com.comunica.news.dto.PostEnviadoDto;
import com.comunica.news.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("posts/")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/salvarPost")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarNoticia(@RequestBody @Valid PostDto noticia, @RequestHeader("Authorization") String token) {
        postService.criarPost(noticia, token);
    }

    @GetMapping("/getAllPosts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostEnviadoDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/getAllMyPosts/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostEnviadoDto> getAllPosts(@PathVariable String idUser) {
        return postService.getAllMyPosts(idUser);
    }

    @GetMapping("/getPostById/{idPost}")
    @ResponseStatus(HttpStatus.OK)
    public PostEnviadoDto getPostById(@PathVariable String idPost) {
        return postService.getPostById(idPost);
    }
}
