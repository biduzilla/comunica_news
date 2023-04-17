package com.comunica.news.service.impl;

import com.comunica.news.exception.EmailNaoEncontrado;
import com.comunica.news.exception.SenhaInvalidaException;
import com.comunica.news.exception.TokenInvalidoException;
import com.comunica.news.models.Usuario;
import com.comunica.news.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceAuthImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = userRepository.findByEmail(username).orElseThrow(EmailNaoEncontrado::new);

        String[] roles = usuario.isAdmin() ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User.
                builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    public UserDetails autentificar(Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getEmail());
        boolean senhaBatem = encoder.matches(usuario.getSenha(), user.getPassword());

        if (senhaBatem) {
            return user;
        } else {
            throw new SenhaInvalidaException();
        }
    }

    public Usuario searchUserByToken(String token) {
        if (token.contains("Bearer")) {
            token = token.split(" ")[1];
        }
        return userRepository.findByToken(token).orElseThrow(TokenInvalidoException::new);
    }
}
