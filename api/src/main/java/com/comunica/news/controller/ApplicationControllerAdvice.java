package com.comunica.news.controller;

import com.comunica.news.erros.ApiError;
import com.comunica.news.exception.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(EmailJaCadastrado.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleEmailJaCadastrado(EmailJaCadastrado ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(EmailNaoEncontrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleEmailNaoEncontrado(EmailNaoEncontrado ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleSenhaInvalidaException(SenhaInvalidaException ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(TokenInvalidoException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiError handleTokenInvalidoException(TokenInvalidoException ex) {
        return new ApiError(ex.getMessage());
    }
    @ExceptionHandler(UserNaoEncontrado.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleUserNaoEncontrado(UserNaoEncontrado ex) {
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodNotValidException(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        return new ApiError(erros);
    }
}
