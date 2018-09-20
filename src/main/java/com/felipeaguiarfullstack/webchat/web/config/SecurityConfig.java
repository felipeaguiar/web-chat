package com.felipeaguiarfullstack.webchat.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	    	.antMatchers("/javax.faces.resource/**", "/cadastro.xhtml")
	        .permitAll().anyRequest().authenticated();

	    http.formLogin()
	    	.loginPage("/login.xhtml").permitAll()
	    	.defaultSuccessUrl("/chat.html", true)
	        .failureUrl("/login.xhtml?error=true");

	    http.logout().logoutSuccessUrl("/login.xhtml");

	    http.csrf().disable();
	}
	
	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}

}
