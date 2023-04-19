package com.comunica.news.models;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "tab_comentario")
public class Comentario {

    @Id
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "comentario")
    private String comentario;

    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne
    private Post post;
}
