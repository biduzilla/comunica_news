package com.comunica.news.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class NoticiaDto {
    @NotEmpty(message = "{campo.titulo.obrigatorio}")
    private String titulo;

    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @NotEmpty(message = "{campo.img.obrigatorio}")
    private String img;
}
