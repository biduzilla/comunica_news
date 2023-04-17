package com.comunica.news.service;

import com.comunica.news.dto.UserDto;
import com.comunica.news.dto.UserLoginDto;

public interface UserService {
    UserDto cadastrarUser(UserDto usuario);

    UserDto login(UserLoginDto login);
}
