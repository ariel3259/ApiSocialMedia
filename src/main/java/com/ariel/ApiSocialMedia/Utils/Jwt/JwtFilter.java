package com.ariel.ApiSocialMedia.Utils.Jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    
    @Autowired
    private UserDetailsService service;

    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String rawToken = request.getHeader("Authorization");
        String token = null;
        String username = null;
        UserDetails user;
        if(rawToken != null && rawToken.startsWith("Bearer ")){
            token = rawToken.substring(7);
            try{
                username = tokenManager.getUsernameFromToken(token);
            }catch(IllegalArgumentException e){
                System.out.println("EMPTY TOKEN");
            }catch(ExpiredJwtException ex){
                System.out.println("EXPIRED TOKEN");
            }catch(SignatureException ex){
                System.out.println("WRONG TOKEN");
            }
        }
        else{
            System.out.println("NO TOKEN");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            user = service.loadUserByUsername(username);
            if(tokenManager.validateToken(user, token)){
                UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(user, null,  user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(userAuth);
            }
        }

        filterChain.doFilter(request, response);
        
    }


}
