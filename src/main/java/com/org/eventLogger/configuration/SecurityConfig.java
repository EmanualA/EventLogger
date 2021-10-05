package com.org.eventLogger.configuration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.net.http.HttpRequest;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    String[] byPassSecurityUris = {
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/webjars/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST).permitAll()
                .antMatchers(HttpMethod.GET).permitAll();


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(byPassSecurityUris);
    }
}
