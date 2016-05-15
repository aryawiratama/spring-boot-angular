/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arya.latihan.config;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

/**
 *
 * @author Artha
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic()
        .and()
            .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/bower_components/**").permitAll()
                .antMatchers("/app/**/*.js").permitAll()
                .antMatchers("/app/**/login.html").permitAll()
                .anyRequest().authenticated().and().csrf().disable();
//        .and()
//            .addFilterAfter(csrfHeaderFilter(), CsrfFilter.class)
//            .csrf().csrfTokenRepository(csrfTokenRepository());
                
    }
 
    /**
     * Method untuk menyimpan CSRF TOKEN di cookie browser.
     * Token disimpan dengan nama XSRF-TOKEN karena AngularJS mengenal CSRF sebagai XSRF
     * @return Filter
     */
    private Filter csrfHeaderFilter(){
        return new OncePerRequestFilter() {

            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
                if(csrfToken != null){
                    String token = csrfToken.getToken();
                    Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");// angular js menamai CSRF dengan XSRF
                    if(cookie==null || token != null && !token.equals(cookie.getValue())){
                        cookie = new Cookie("XSRF-TOKEN", token);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }
                filterChain.doFilter(request, response);
            }
        };
    }
    
    /**
     * Method untuk mengubah format CSRF TOKEN menjadi format yang dikenali oleh AngularJS.
     * Format default adalah X-CSRF-TOKEN diubah menjadi X-XSRF-TOKEN
     * @return HttpSessionCsrfTokenRepository
     */
    private CsrfTokenRepository csrfTokenRepository(){
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }
    
}