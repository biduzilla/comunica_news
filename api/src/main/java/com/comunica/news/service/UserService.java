package com.comunica.news.service;

import com.comunica.news.dto.TokenDto;
import com.comunica.news.dto.UserDto;
import com.comunica.news.dto.UserLoginDto;
import com.comunica.news.dto.UserUpdateDto;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    void cadastrarUser(UserDto usuario);
    TokenDto login(UserLoginDto login);
    @Transactional
    void atualizar(UserUpdateDto userDto, String token);
    @Transactional
    void deletarUser(String token);

}
