package com.comunica.news.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "tab_noticia")
public class Noticia {

    @Id
    private String id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "img")
    private String img;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "autor")
    private String autor;

    @JoinColumn(name = "usuario_id", nullable = false)
    @ManyToOne
    private Usuario usuario;
}
