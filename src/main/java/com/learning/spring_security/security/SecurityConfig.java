package com.learning.spring_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.http.UserDetailsServiceFactoryBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*@Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests
                    .requestMatchers("/contact").permitAll()
                    .requestMatchers("/public/**").permitAll()
                    .requestMatchers("/admin/**").denyAll()
                    .anyRequest())
                    .authenticated();
        });
//        http.formLogin(Customizer.withDefaults());
        http.sessionManagement(session->{
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        http.csrf(AbstractHttpConfigurer::disable);

        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain)http.build();
    }*/

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> {
            ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)requests
                    .anyRequest())
                    .authenticated();
        });
//        http.formLogin(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(Customizer.withDefaults());
        return (SecurityFilterChain)http.build();
    }


/*
    // This is for InMemory based authentication
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        if (!manager.userExists("user")) {
            manager.createUser(User
                    .withUsername("user1")
                    .password("{noop}password") // {noop} is for plain text password storage
                    .roles("USER")
                    .build());
        }
        if (!manager.userExists("admin")) {
            manager.createUser(User
                    .withUsername("admin")
                    .password("{noop}admin") // {noop} is for plain text password storage
                    .roles("ADMIN")
                    .build());
        }
        return manager;
    }
*/

    // This is for JDBC based authentication- using mysql database
    @Bean
    public UserDetailsService userDetailsService(DataSource ds) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(ds);
        if (!manager.userExists("user1")) {
            manager.createUser(User
                    .withUsername("user1")
                    .password("{noop}password") // {noop} is for plain text password storage
                    .roles("USER")
                    .build());
        }

        if (!manager.userExists("admin")) {
            manager.createUser(User
                    .withUsername("admin")
                    .password("{noop}admin") // {noop} is for plain text password storage
                    .roles("ADMIN")
                    .build());
        }
        return manager;
    }


}
