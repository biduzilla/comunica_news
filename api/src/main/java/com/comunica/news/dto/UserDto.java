package com.comunica.news.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDto {
    @NotEmpty(message = "{campo.nome.obrigatório}")
    private String nome;
    @NotEmpty(message = "{campo.email.obrigatório}")
    private String email;
    @NotEmpty(message = "{campo.senha.obrigatório}")
    private String senha;
}
