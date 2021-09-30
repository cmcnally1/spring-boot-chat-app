package com.cmcnally.jwdnd.c1.review.config;

import com.cmcnally.jwdnd.c1.review.service.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
    Security configuration to detail the authentication config and HTTP request security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Variable for the authentication service
    private AuthenticationService authenticationService;

    // Constructor
    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // Configure Spring to use Authentication Service to check logins
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationService);
    }

    // Configure the security requirements of HTTP requests
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Permit unauthenticated users to access the signup page and site artifacts
        // and permit authenticated users to access any page
        http.authorizeRequests()
                .antMatchers("/signup", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();

        // Set the login page and allow all users to access
        http.formLogin()
                .loginPage("/login")
                .permitAll();

        // Set the home page to be loaded on authentication success at login
        http.formLogin()
                .defaultSuccessUrl("/home", true);
    }
}
