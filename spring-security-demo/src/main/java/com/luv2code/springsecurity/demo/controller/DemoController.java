/**
 * 
 */
package com.luv2code.springsecurity.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Mihai-Tudor Popescu
 *
 */
@Controller
public class DemoController {
	
	@GetMapping("/")
	public String showHome() {
		return "home";
	}
	
	
	//add a request mapping for /leaders
	@GetMapping("/leaders")
	public String showLeaderView() {
		return "leaders";
	}
}
