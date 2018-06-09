/**
 * 
 */
package com.luv2code.springsecurity.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

import com.luv2code.springsecurity.demo.genericsettings.UrlConstants;

/**
 * @author Mihai-Tudor Popescu
 *
 */

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {



	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// add users for in-memory authentication
		UserBuilder users = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication()
			.withUser(users.username("adrian").password("ady123").roles("EMPLOYEE"))
			.withUser(users.username("karl").password("karl123").roles("MANAGER"))
			.withUser(users.username("michael").password("mike123").roles("ADMIN"));
	}
	
	@Override
	protected void configure(HttpSecurity httpSec) throws Exception{
		httpSec.authorizeRequests()
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage(UrlConstants.CUSTOM_LOGIN_PAGE)
				.loginProcessingUrl(UrlConstants.AUTHENTICATE_USER_URL)
				.permitAll();
	}
	
}
