/**
 * uses the fetched user information from web service to validate the supplied credentials.
 */

package com.proximotech.zhw;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class WebServiceAuthenticationProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		if("guest".equalsIgnoreCase(username) && "guest".equalsIgnoreCase(password)) {
			return new UsernamePasswordAuthenticationToken(username, password,Collections.emptyList());
		}else {
			throw new BadCredentialsException(" Authentication failed ");
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
