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



	private static final String SYSTEMS_PATH = "/systems/**";
	private static final String LEADERS_PATH = "/leaders/**";
	private static final String ROOT_PATH = "/";
	private static final String ADMIN_ROLE = "ADMIN";
	private static final String MANAGER_ROLE = "MANAGER";
	private static final String EMPLOYEE_ROLE = "EMPLOYEE";

	/* (non-Javadoc)
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// add users for in-memory authentication
		UserBuilder users = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication()
			.withUser(users.username("adrian").password("ady123").roles(EMPLOYEE_ROLE))
			.withUser(users.username("karl").password("karl123").roles(EMPLOYEE_ROLE, MANAGER_ROLE))
			.withUser(users.username("michael").password("mike123").roles(EMPLOYEE_ROLE, ADMIN_ROLE));
	}
	
	@Override
	protected void configure(HttpSecurity httpSec) throws Exception{
		httpSec.authorizeRequests()
			.antMatchers(ROOT_PATH).hasRole(EMPLOYEE_ROLE)
			.antMatchers(LEADERS_PATH).hasRole(MANAGER_ROLE)
			.antMatchers(SYSTEMS_PATH).hasRole(ADMIN_ROLE)
			.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage(UrlConstants.CUSTOM_LOGIN_PAGE)
				.loginProcessingUrl(UrlConstants.AUTHENTICATE_USER_URL)
				.permitAll()
			.and()
				.logout()
				.permitAll();
	}
	
}
