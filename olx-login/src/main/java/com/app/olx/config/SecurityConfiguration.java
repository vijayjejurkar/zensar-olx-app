package com.app.olx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {//override to default authentication
		
		auth.userDetailsService(userDetailsService);
		/*auth.inMemoryAuthentication()
		.withUser("tom").password(getPasswordEncoder().encode("tom")).roles("USER")
		.and()
		.withUser("jerry").password(getPasswordEncoder().encode("jerry")).roles("ADMIN");*/
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {//override to default authorization.
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasAnyRole("ADMIN", "USER")
		.antMatchers("/all", "/authenticate", "/read-property", "/actuator/refresh", "/actuator").permitAll()
		.and()
		.formLogin();
	}



	@Bean
	public NoOpPasswordEncoder getPasswordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception{
		return super.authenticationManager();
	}
	
}
