package com.comunica.news.repository;

import com.comunica.news.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, String> {
    Optional<Post> findById(String idUser);

    Optional<List<Post>> findAllByUsuarioId(String userId);
}
