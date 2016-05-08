/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.arya.latihan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Artha
 */
@Configuration
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
//                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();
                
    }
    
}