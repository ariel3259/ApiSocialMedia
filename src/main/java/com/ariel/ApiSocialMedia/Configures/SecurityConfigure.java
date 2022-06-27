package com.ariel.ApiSocialMedia.Configures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ariel.ApiSocialMedia.Utils.Jwt.JwtFilter;

@Configuration
public class SecurityConfigure  {
    
    @Autowired
    private JwtFilter filter;
    
    @Autowired
    private AuthenticationEntryPoint entry;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain httpFilterChain(HttpSecurity http) throws Exception {
        http.cors()
            .and()
            .csrf().disable()
            .authorizeHttpRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/api2/users/register").permitAll()
            .antMatchers("/api/**").permitAll()
            .antMatchers("/v2/**").permitAll()
            .antMatchers("/swagger-ui/**").permitAll()
            .antMatchers("/webjars/**").permitAll()
            .antMatchers("/swagger-resources/**").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(entry)
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
            return http.build();
        }

}
