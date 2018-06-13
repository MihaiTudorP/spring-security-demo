/**
 * 
 */
package com.luv2code.springsecurity.demo.genericsettings;

/**
 * @author Mihai-Tudor Popescu
 *
 */
public class UrlConstants {
	public static final String AUTHENTICATE_USER_URL = "/authenticateUser";
	public static final String CUSTOM_LOGIN_PAGE = "/customLoginPage";
	public static final String SYSTEMS_PATH = "/systems/**";
	public static final String LEADERS_PATH = "/leaders/**";
	public static final String ROOT_PATH = "/";
	
	public static final String REGISTER_URL = "/register";
	public static final String REGISTRATION_FORM_URL = "/registrationForm";
	public static final String PROCESS_REGISTRATION_FORM_URL = "/processRegistrationForm";
	
	public static final String SYSTEMS_MAIN_VIEW_URL = "/systems";
	public static final String LEADERS_MAIN_VIEW_URL = "/leaders";
	public static final String ACCESS_DENIED_VIEW = "/access-denied";
	
	public static final String RESOURCES_DIR_PATH = "/resources/";
	public static final String RESOURCES_RECURSIVE_PATH = "/resources/**";
	
	public static final String CUSTOMER_PAGE_ROOT_URL = "/customer/**";
	public static final String CUSTOMER_PAGE_URL = "/customer";
	public static final String CUSTOMER_DELETE_URL = "/customer/delete";
	public static final String CUSTOMER_SAVE_URL = "/customer/save*";
	public static final String CUSTOMER_SHOW_FORM_URL = "/customer/showForm*";
	
	public static final String DELETE_URL = "/delete";
	public static final String UPDATE_CUSTOMER_FORM_URL = "/updateCustomerForm";
	public static final String SAVE_CUSTOMER_URL = "/saveCustomer";
	public static final String ADD_CUSTOMER_FORM_URL = "/addCustomerForm";
	public static final String LIST_URL = "/list";
}
