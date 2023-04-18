package com.comunica.news.security;

import com.comunica.news.service.impl.UsuarioServiceAuthImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UsuarioServiceAuthImpl usuarioServiceAuth;

    public JwtAuthFilter(JwtService jwtService, UsuarioServiceAuthImpl usuarioService) {
        this.jwtService = jwtService;
        this.usuarioServiceAuth = usuarioService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer")) {
            String token = authorization.split(" ")[1];

            boolean isValid = jwtService.tokenValid(token);
            if (isValid) {
                String loginUser = jwtService.obterLoginUser(token);
                UserDetails usario = usuarioServiceAuth.loadUserByUsername(loginUser);
                UsernamePasswordAuthenticationToken user = new
                        UsernamePasswordAuthenticationToken(usario, null, usario.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }
        filterChain.doFilter(request,response);
    }
}
