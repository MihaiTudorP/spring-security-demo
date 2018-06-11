/**
 * 
 */
package com.luv2code.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.luv2code.springsecurity.demo.genericsettings.UrlConstants;
import com.luv2code.springsecurity.demo.genericsettings.ViewNameConstants;

/**
 * @author Mihai-Tudor Popescu
 *
 */
@Controller
public class DemoController {

	@GetMapping(UrlConstants.ROOT_PATH)
	public String showHome() {
		return ViewNameConstants.HOME_VIEW_NAME;
	}

	//add a request mapping for /leaders
	@GetMapping(UrlConstants.LEADERS_MAIN_VIEW_URL)
	public String showLeaderView() {
		return ViewNameConstants.LEADERS_MAIN_VIEW_NAME;
	}

	//add a request mapping for /systems
	@GetMapping(UrlConstants.SYSTEMS_MAIN_VIEW_URL)
	public String showAdminView() {
		return ViewNameConstants.SYSTEMS_MAIN_VIEW_NAME;
	}
	
	@GetMapping(UrlConstants.ACCESS_DENIED_VIEW)
	public String showAccessDenied() {
		return ViewNameConstants.ACCESS_DENIED_VIEW_NAME;
	}
}
