package com.comunica.news.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserLoginDto {
    private String email;
    private String senha;
}
