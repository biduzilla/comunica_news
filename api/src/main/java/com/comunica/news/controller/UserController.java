package com.comunica.news.controller;

import com.comunica.news.dto.TokenDto;
import com.comunica.news.dto.UserDto;
import com.comunica.news.dto.UserLoginDto;
import com.comunica.news.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("cadastrar/")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarConta(@RequestBody @Valid UserDto user) {
        userService.cadastrarUser(user);
    }

    @PostMapping("login/")
    @ResponseStatus(HttpStatus.OK)
    public TokenDto login(@RequestBody @Valid UserLoginDto userLoginDto) {
        return userService.login(userLoginDto);
    }
}
