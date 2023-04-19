package com.comunica.news.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ComentarioDto {
    private String id;
    @NotEmpty(message = "{campo.comentario.obrigatorio}")
    private String comentario;
}
