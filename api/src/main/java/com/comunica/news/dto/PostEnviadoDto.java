package com.comunica.news.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PostEnviadoDto {
    private String id;
    private String titulo;
    private String descricao;
    private String img;
    private String prazo;
    private String autor;
    private List<ComentarioDto> comentarios;
}
