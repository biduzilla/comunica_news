package com.comunica.news.service.impl;

import com.comunica.news.dto.TokenDto;
import com.comunica.news.dto.UserDto;
import com.comunica.news.dto.UserLoginDto;
import com.comunica.news.dto.UserUpdateDto;
import com.comunica.news.exception.EmailJaCadastrado;
import com.comunica.news.exception.UserNaoEncontrado;
import com.comunica.news.models.Usuario;
import com.comunica.news.repository.UserRepository;
import com.comunica.news.security.JwtService;
import com.comunica.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UsuarioServiceAuthImpl usuarioServiceAuth;
    private final JwtService jwtService;

    @Override
    public void cadastrarUser(UserDto usuarioDto) {
        if (!userRepository.existsByEmail(usuarioDto.getEmail())) {

            Usuario usuario = Usuario.builder()
                    .admin(false)
                    .id(UUID.randomUUID().toString())
                    .email(usuarioDto.getEmail())
                    .senha(encoder.encode(usuarioDto.getSenha()))
                    .nome(usuarioDto.getNome())
                    .build();

            userRepository.save(usuario);
        } else {
            throw new EmailJaCadastrado();
        }
    }

    @Override
    public TokenDto login(UserLoginDto login) {
        try {
            Usuario usuario =
                    Usuario.builder()
                            .email(login.getEmail())
                            .senha(login.getSenha())
                            .build();

            UserDetails userAutentificado = usuarioServiceAuth.autentificar(usuario);
            String token = jwtService.gerarToken(usuario);

            Usuario userPronto = userRepository.findByEmail(usuario.getEmail()).orElseThrow(UserNaoEncontrado::new);
            userPronto.setToken(token);
            userRepository.save(userPronto);

            return new TokenDto(token, userPronto.getId());

        } catch (UsernameNotFoundException e) {
            throw new UserNaoEncontrado();
        }
    }

    @Override
    public void atualizar(UserUpdateDto userDto, String token) {
        Usuario usuario = usuarioServiceAuth.searchUserByToken(token);

        String senhaCriptografada = encoder.encode(userDto.getSenha());
        userDto.setSenha(senhaCriptografada);

        usuario.setSenha(userDto.getSenha());
        usuario.setNome(userDto.getNome());

        userRepository.save(usuario);
    }

    @Override
    public void deletarUser(String idUser) {
        Usuario usuario = userRepository.findById(idUser).orElseThrow(UserNaoEncontrado::new);

        userRepository.delete(usuario);
    }

    @Override
    public UserDto getDados(String idUser) {
        Usuario usuario = userRepository.findById(idUser).orElseThrow(UserNaoEncontrado::new);
        return UserDto.builder()
                .email(usuario.getEmail())
                .nome(usuario.getSenha())
                .build();
    }

}
