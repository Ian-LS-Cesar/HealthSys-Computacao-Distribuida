package com.healthsys.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod; // <-- added

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
                        // Include both "/usuarios" and "/auth/usuarios" to cover cases where a gateway
                        // preserves or rewrites the path.
                        .requestMatchers(HttpMethod.POST, "/usuarios", "/auth/usuarios").permitAll()

                        // Allow unauthenticated access to login/logout, validate and actuator endpoints
                        .requestMatchers("/login", "/validate", "/logout", "/actuator/**").permitAll()

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