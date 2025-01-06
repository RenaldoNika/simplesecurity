package com.example.Security.config;

import com.example.Security.entity.UserPersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfiguration {


    @Autowired
    PersonAuthenticationSuccessHandler personAuthenticationSuccessHandler;
    @Autowired
    UserPersonDetailsService userPersonDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(c->c.disable())
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/home/**").permitAll()
                .requestMatchers("/post").permitAll()
                .requestMatchers("/admin").hasRole("admin")
                .requestMatchers("/superadmin").hasRole("superadmin")
                .anyRequest().permitAll()
                )
                .formLogin(form->form
                        .successHandler(personAuthenticationSuccessHandler)
                        .permitAll()
                )
                .userDetailsService(userPersonDetailsService);
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}