package com.comunica.news.controller;

import com.comunica.news.erros.ApiError;
import com.comunica.news.exception.EmailJaCadastrado;
import com.comunica.news.exception.EmailNaoEncontrado;
import com.comunica.news.exception.SenhaInvalidaException;
import com.comunica.news.exception.TokenInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleTokenInvalidoException(TokenInvalidoException ex) {
        return new ApiError(ex.getMessage());
    }
}
