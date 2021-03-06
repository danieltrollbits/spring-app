package com.training.spring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Autowired
	CustomAuthenticationSuccessHandler successHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

	    http.authorizeRequests()
        .antMatchers("/resources/**","/create_account","/denied").permitAll()
        .antMatchers("/delete","/audit","/pending_account","/accept_account").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        .and()
        	.formLogin()
        	.loginPage("/login")
        	.loginProcessingUrl("/j_spring_security_login")
        	.usernameParameter("username")
        	.passwordParameter("password")
        	.successHandler(successHandler)
        	.permitAll()
        .and()
        	.logout()
        	.permitAll()
        .and()
        	.csrf()
        .and()
        	.exceptionHandling()
        	.accessDeniedPage("/denied");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
}
