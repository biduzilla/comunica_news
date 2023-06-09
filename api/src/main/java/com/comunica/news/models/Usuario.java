package com.comunica.news.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "tab_user")
public class Usuario {

    @Id
    private String id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "token")
    private String token;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "admin")
    private boolean admin = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Post> posts;

}
