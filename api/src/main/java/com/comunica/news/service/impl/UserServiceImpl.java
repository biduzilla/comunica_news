package com.comunica.news.service.impl;

import com.comunica.news.dto.UserDto;
import com.comunica.news.dto.UserLoginDto;
import com.comunica.news.exception.EmailJaCadastrado;
import com.comunica.news.models.Usuario;
import com.comunica.news.repository.UserRepository;
import com.comunica.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    @Override
    public UserDto cadastrarUser(UserDto usuarioDto) {
        if (!userRepository.existsByEmail(usuarioDto.getEmail())) {

            Usuario usuario = Usuario.builder()
                    .admin(false)
                    .id(UUID.randomUUID().toString())
                    .email(encoder.encode(usuarioDto.getSenha()))
                    .senha(usuarioDto.getSenha())
                    .nome(usuarioDto.getNome())
                    .build();

            userRepository.save(usuario);
            return usuarioDto;
        } else {
            throw new EmailJaCadastrado();
        }
    }

    @Override
    public UserDto login(UserLoginDto login) {
        return null;
    }
}
