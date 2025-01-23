package com.example.todolist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурация безопасности для веб-приложения.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Конфигурация шифрования паролей.
     *
     * @return объект PasswordEncoder для шифрования паролей.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Настройка пользовательского сервиса для аутентификации.
     *
     * @param passwordEncoder объект для шифрования паролей.
     * @return UserDetailsService с предопределенными пользователями.
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build());
        manager.createUser(User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .roles("USER", "ADMIN")
                .build());
        return manager;
    }

    /**
     * Конфигурация фильтра безопасности для обработки HTTP-запросов.
     *
     * @param http Конфигурация безопасности HTTP.
     * @return Фильтр безопасности для цепочки фильтров.
     * @throws Exception В случае ошибок конфигурации.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/tasks")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .permitAll()
                        .logoutSuccessUrl("/login"))
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/login")
                );

        return http.build();
    }
}
