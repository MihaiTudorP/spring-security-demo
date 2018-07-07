package com.luv2code.springsecurity.demo.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springsecurity.demo.entities.Customer;
import com.luv2code.springsecurity.demo.genericsettings.UrlConstants;
import com.luv2code.springsecurity.demo.genericsettings.ViewNameConstants;
import com.luv2code.springsecurity.demo.services.CustomerService;

@Controller
@RequestMapping(UrlConstants.CUSTOMER_PAGE_URL)
public class CustomerController {
	
	private static final String CUSTOMER_ID_PARAM_NAME = "customerId";

	private static final String CUSTOMER_ATTRIBUTE_NAME = "customer";

	private static final String REDIRECT_CUSTOMER_LIST_URL = "redirect:/customer/list";
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping(UrlConstants.LIST_URL)
	public String listCustomers(Model model) {
		logger.info("Displaying the customers in the database...");
		
		// get customers from the service
		List<Customer> customers = customerService.getCustomers();
		
		logger.info("Got "+ customers.size() + " customers from the database. Passing them to the view.");
				
		// add the customers to the model
		model.addAttribute("customers", customers);
		
		return ViewNameConstants.LIST_CUSTOMERS_VIEW_NAME;
	}
	
	@GetMapping(UrlConstants.ADD_CUSTOMER_FORM_URL)
	public String showFormForAdd(Model model) {
		
		// create model attribute to bind form data
		Customer customer = new Customer();
		
		model.addAttribute(CUSTOMER_ATTRIBUTE_NAME, customer);
		
		return ViewNameConstants.CUSTOMER_FORM_VIEW_NAME;
	}
	
	@PostMapping(UrlConstants.SAVE_CUSTOMER_URL)
	@Transactional
	public String saveCustomer(@ModelAttribute(CUSTOMER_ATTRIBUTE_NAME) Customer customer) {
		
		// save the customer using our service
		customerService.saveCustomer(customer);	
		
		return REDIRECT_CUSTOMER_LIST_URL;
	}
	
	@GetMapping(UrlConstants.UPDATE_CUSTOMER_FORM_URL)
	@Transactional
	public String showFormForUpdate(@RequestParam(CUSTOMER_ID_PARAM_NAME) int id,
									Model model) {
		
		// get the customer from our service
		Customer customer = customerService.getCustomer(id);
		
		logger.info("Editing customer: " + customer.toString());
		
		// set customer as a model attribute to pre-populate the form
		model.addAttribute(CUSTOMER_ATTRIBUTE_NAME, customer);
		
		// send over to our form		
		return ViewNameConstants.CUSTOMER_FORM_VIEW_NAME;
	}
	
	@GetMapping(UrlConstants.DELETE_URL)
	public String deleteCustomer(@RequestParam(CUSTOMER_ID_PARAM_NAME) int id) {
		
		// delete the customer
		customerService.deleteCustomer(id);
		
		return REDIRECT_CUSTOMER_LIST_URL;
	}
}
