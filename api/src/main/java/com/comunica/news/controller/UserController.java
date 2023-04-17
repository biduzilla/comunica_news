package com.comunica.news.controller;

import com.comunica.news.dto.UserDto;
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
    public UserDto criarConta(@RequestBody @Valid UserDto user) {
        return userService.cadastrarUser(user);
    }
}
