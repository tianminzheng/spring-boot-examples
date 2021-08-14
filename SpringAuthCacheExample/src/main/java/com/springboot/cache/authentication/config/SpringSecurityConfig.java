package com.springboot.cache.authentication.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private DataSource dataSource;
	
	@Autowired
	private UserCache userCache;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
    	 auth
         .eraseCredentials(false)
         .authenticationProvider(authenticationProviderBean())
         .jdbcAuthentication()
         .dataSource(dataSource);  
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationProvider authenticationProviderBean() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserCache(userCache);
        authenticationProvider.setUserDetailsService(userDetailsService());
        
        return authenticationProvider;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
            .anyRequest().authenticated()
        .and()
            .httpBasic();
    }

    
}
