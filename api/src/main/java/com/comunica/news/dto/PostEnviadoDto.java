package com.comunica.news.dto;

import lombok.*;

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
}
