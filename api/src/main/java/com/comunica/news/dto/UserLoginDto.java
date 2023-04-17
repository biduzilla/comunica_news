package com.comunica.news.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserLoginDto {
    @NotEmpty(message = "{campo.email.obrigatorio}")
    private String email;
    @NotEmpty(message = "{campo.senha.obrigatório}")
    private String senha;
}
