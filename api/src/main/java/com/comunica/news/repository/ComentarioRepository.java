package com.comunica.news.repository;

import com.comunica.news.models.Comentario;
import com.comunica.news.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ComentarioRepository extends JpaRepository<Comentario, String> {

    List<Comentario> findAllByPostId(String postId);
}
