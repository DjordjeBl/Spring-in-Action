package com.example.demo.security;


import static org.springframework.security.config.Customizer.withDefaults;

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
    return new BCryptPasswordEncoder();
  }

  // TODO: 20-Sep-23  
//  @Bean
//  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//    http
//        .authorizeRequests(authorizeRequests -> {
//          authorizeRequests
//              .antMatchers("/design", "/orders").hasRole("USER")
//              .antMatchers("/", "/**").permitAll();
//        })
//        .formLogin(withDefaults())
//        .loginPage("/login");
//
//    return http.build();
//  }

}
