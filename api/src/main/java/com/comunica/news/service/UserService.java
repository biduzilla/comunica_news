package com.comunica.news.service;

import com.comunica.news.dto.TokenDto;
import com.comunica.news.dto.UserDto;
import com.comunica.news.dto.UserLoginDto;

public interface UserService {
    void cadastrarUser(UserDto usuario);

    TokenDto login(UserLoginDto login);
}
