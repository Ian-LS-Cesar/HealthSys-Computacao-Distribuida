package com.healthsys.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)

                // 1. DESATIVA O LOGOUT AUTOMÁTICO DO SPRING SECURITY
                .logout(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        // Allow unauthenticated access to create user (registration).
                        // Allow unauthenticated access to login/logout, validate and actuator endpoints
                        .requestMatchers("/login", "/validate", "/logout", "/actuator/**", "/perfis", "/usuarios", "/auth/usuarios", "/especialidades").permitAll()

                        // everything else must be authenticated
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}