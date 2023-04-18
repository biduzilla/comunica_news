package com.comunica.news.repository;

import com.comunica.news.models.Noticia;
import com.comunica.news.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticiaRepository extends JpaRepository<Noticia, String> {
    Optional<Noticia> findById(String idUser);
}
