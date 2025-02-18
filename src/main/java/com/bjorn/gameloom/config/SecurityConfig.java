package com.bjorn.gameloom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean //chatgpt
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for development (enable in production)
            .authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin/**").hasRole("ADMIN")  // Only ADMIN can access
                .requestMatchers("/public/**").permitAll()  // Public endpoints (no authentication needed)
                .anyRequest().authenticated())  // All other requests require authentication
            .formLogin(login -> login  // Enable form-based login
                .defaultSuccessUrl("/welcome", true))
            .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"));

        return http.build();
    }

}
