/**
 * 
 */
package com.proximotech.zhw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Apple
 *,
 */
@EnableWebSecurity
public class WebServiceAuthProviderSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	WebServiceAuthenticationProvider customAuthProvider;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().and().authorizeRequests().antMatchers("/home").authenticated();
//		http.httpBasic().and().authorizeRequests().antMatchers("/").authenticated();
	}

	@Bean   
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
