/* 

package com.microservicio.alertas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Deshabilitado para facilitar pruebas en microservicios
            .authorizeRequests()
            .antMatchers("/api/alertas/**").authenticated() // Todo endpoint requiere JWT
            .and()
            .httpBasic(); // Configuración base, idealmente aquí inyectas tu JwtFilter
            
        return http.build();
    }
}

*/