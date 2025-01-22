package com.example.securitytask01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    
    @Bean
        public UserDetailsService userDetailsService() {
                PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
                User.UserBuilder users = User.builder().passwordEncoder(encoder::encode);

                InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
                manager.createUser(users.username("user").password("password").roles("USER").build());
                manager.createUser(users.username("admin").password("password").roles("USER", "ADMIN").build());

        return manager;
}


  
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/public-data").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/private-data").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .permitAll()
                        .logoutSuccessUrl("/login"))
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage(("/login"))
                );

        return http.build();
    }


}
