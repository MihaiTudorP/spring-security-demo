/**
 * 
 */
package com.luv2code.springsecurity.demo.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springsecurity.demo.entities.CrmUser;
import com.luv2code.springsecurity.demo.genericsettings.UrlConstants;
import com.luv2code.springsecurity.demo.genericsettings.ViewNameConstants;

/**
 * @author Mihai-Tudor Popescu
 *
 */
@Controller
@RequestMapping(UrlConstants.REGISTER_URL)
public class RegistrationController {

	private static final String REGISTRATION_ERROR_MODEL_KEY = "registrationError";

	private static final String CRM_USER_MODEL_ATTRIBUTE = "crmUser";
	
	@Autowired
	private UserDetailsManager userDetailsManager;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {		
		dataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@GetMapping(UrlConstants.REGISTRATION_FORM_URL)
	public String showLoginPage(Model model) {
		model.addAttribute(CRM_USER_MODEL_ATTRIBUTE, new CrmUser());
		return ViewNameConstants.REGISTRATION_FORM_VIEW_NAME;
	}
	
	@PostMapping(UrlConstants.PROCESS_REGISTRATION_FORM_URL)
	public String processRegistrationForm(@Valid @ModelAttribute(RegistrationController.CRM_USER_MODEL_ATTRIBUTE) CrmUser crmUser, BindingResult bindingResult, Model model) {
		logger.info("Processing registration for: " + crmUser.getUserName());
		
		if (bindingResult.hasErrors()) {
			model.addAttribute(CRM_USER_MODEL_ATTRIBUTE, new CrmUser());
			model.addAttribute(REGISTRATION_ERROR_MODEL_KEY, "User name/password cannot be empty.");
			
			logger.warning("Empty user name or password submitted.");
			return ViewNameConstants.REGISTRATION_FORM_VIEW_NAME;
		}
			
		if(theUserExists(crmUser.getUserName())) {
			model.addAttribute(CRM_USER_MODEL_ATTRIBUTE, new CrmUser());
			model.addAttribute(REGISTRATION_ERROR_MODEL_KEY, "User name already exists.");
			logger.warning("Attempted to add an already existing user");
			return ViewNameConstants.REGISTRATION_FORM_VIEW_NAME;
		}
		
		String encodedPassword = "{bcrypt}" + passwordEncoder.encode(crmUser.getPassword());
		
		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_EMPLOYEE");
		
		User user = new User(crmUser.getUserName(), encodedPassword, authorities);
		
		userDetailsManager.createUser(user);
		
		logger.info("Successfully created user " + crmUser.getUserName());
		
		return ViewNameConstants.REGISTRATION_CONFIRMATION_VIEW_NAME;
		
	}

	private boolean theUserExists(String userName) {
		logger.info("Checking if the user " + userName + " already exists...");
		boolean userExists = userDetailsManager.userExists(userName);
		logger.info("The user " + userName + (userExists?" already exists.":" is not present in the database."));
		return userExists;
	}
}
