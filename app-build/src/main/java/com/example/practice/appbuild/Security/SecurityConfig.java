package com.example.practice.appbuild.Security;

import com.example.practice.appbuild.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserService userService;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/i2c/test").permitAll()
                        .requestMatchers("/i2c/users").permitAll()
                        .requestMatchers("/i2c/addUser").permitAll()
                        .anyRequest().authenticated()
                )
                .userDetailsService(userService)
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
