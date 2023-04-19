package com.comunica.news.service;

import com.comunica.news.dto.PostDto;
import com.comunica.news.dto.PostEnviadoDto;

import java.util.List;

public interface PostService {
    void criarPost(PostDto postDto, String token);
    List<PostEnviadoDto> getAllPosts();
    List<PostEnviadoDto> getAllMyPosts(String idUser);
    PostEnviadoDto getPostById(String postId);
    PostEnviadoDto getMyPostById(String postId, String userId);
    void updatePost(PostDto postDto, String postId);
    void deletePost(String postId);
}
