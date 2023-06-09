package com.comunica.news.config;

import com.comunica.news.security.JwtAuthFilter;
import com.comunica.news.security.JwtService;
import com.comunica.news.service.impl.UsuarioServiceAuthImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceAuthImpl usuarioService;
    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/cadastrar")
                .permitAll()
                .antMatchers("/users/login")
                .permitAll()
                .antMatchers("/users/atualizar")
                .hasRole("USER")
                .antMatchers("/users/deletar/**")
                .hasRole("ADMIN")
                .antMatchers("/noticias/salvarPost")
                .hasRole("ADMIN")
                .antMatchers("/noticias/getAllPosts")
                .hasRole("USER")
                .antMatchers("/noticias/getAllMyPosts/**")
                .hasRole("ADMIN")
                .antMatchers("/noticias/getPostById/**")
                .hasRole("USER")
                .antMatchers("/noticias/updatePost/**")
                .hasRole("ADMIN")
                .antMatchers("/noticias/deletePost/**")
                .hasRole("ADMIN")
                .antMatchers("/h2-console/**")
                .permitAll()
                .antMatchers("/h2-console/")
                .permitAll()
                .antMatchers("/h2-console")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();
    }

}

