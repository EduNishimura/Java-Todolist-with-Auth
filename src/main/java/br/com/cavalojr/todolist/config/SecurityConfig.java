package br.com.cavalojr.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // springboot by default enables Cross-Site Request Forgery (CSRF)
                                              // protection
                                              // to prevent unauthorized requests.
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/signup").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated())
                .build();
    }
}
