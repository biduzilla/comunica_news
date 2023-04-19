package com.comunica.news.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PostDto {
    @NotEmpty(message = "{campo.titulo.obrigatorio}")
    private String titulo;

    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @NotEmpty(message = "{campo.img.obrigatorio}")
    private String img;
}
