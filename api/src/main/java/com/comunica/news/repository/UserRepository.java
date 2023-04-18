package com.comunica.news.repository;

import com.comunica.news.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, String> {

    boolean existsByEmail(String email);
    Optional<Usuario> findByEmail(String login);
    Optional<Usuario> findByToken(String token);
    Optional<Usuario> findById(String idUser);
}
