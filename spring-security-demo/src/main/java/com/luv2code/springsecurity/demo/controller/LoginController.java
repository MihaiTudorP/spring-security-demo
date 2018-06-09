/**
 * 
 */
package com.luv2code.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.luv2code.springsecurity.demo.genericsettings.UrlConstants;


/**
 * @author Mihai-Tudor Popescu
 *
 */
@Controller
public class LoginController {
	@GetMapping(UrlConstants.CUSTOM_LOGIN_PAGE)
	public String showCustomLoginPage() {
		return "login-with-manual-csrf";
	}
}
