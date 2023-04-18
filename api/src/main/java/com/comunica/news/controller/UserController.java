package com.comunica.news.controller;

import com.comunica.news.dto.TokenDto;
import com.comunica.news.dto.UserDto;
import com.comunica.news.dto.UserLoginDto;
import com.comunica.news.dto.UserUpdateDto;
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

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarConta(@RequestBody @Valid UserDto user) {
        userService.cadastrarUser(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenDto login(@RequestBody @Valid UserLoginDto userLoginDto) {
        return userService.login(userLoginDto);
    }

    @PutMapping("/atualizar")
    @ResponseStatus(HttpStatus.OK)
    public void atualizarUser(@RequestBody UserUpdateDto user, @RequestHeader("Authorization") String token) {
        System.out.println(token);
        userService.atualizar(user, token);
    }

    @DeleteMapping("/deletar/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarUser(@PathVariable String idUser) {
        userService.deletarUser(idUser);
    }

    @GetMapping("getDados/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getDados(@PathVariable String idUser) {
        return userService.getDados(idUser);
    }
}
