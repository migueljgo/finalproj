	package com.csb.appadvc2122.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired 
    CustomAuthenticationProvider customAuthenticationProvider;

    private AuthenticationSuccessHandler authenticationSuccessHandler;
    
    @Autowired
    public void WebSecurityConfig(AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }
    
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .csrf().disable()
            .authorizeRequests()
            // Here, we are allowing access to the landing, and register pages to anonymous users only
            .antMatchers("/", "/login*", "/register*","/forgot-password")
                .anonymous()
            // Here, we are allowing access to user pages to the admin role only
            .antMatchers("/user*").hasRole("ADMIN")
            // Here, we are allowing access to product pages to the admin and employee roles only
            .antMatchers("/product*").hasAnyRole("ADMIN", "EMPLOYEE")
            // Any other requests should be authenticated
            .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler)
                .usernameParameter("emailAddress")
            .and()
                .logout()
                .permitAll();
    }

}
