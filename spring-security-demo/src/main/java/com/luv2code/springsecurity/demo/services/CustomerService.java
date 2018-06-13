/**
 * 
 */
package com.luv2code.springsecurity.demo.services;

import java.util.List;

import com.luv2code.springsecurity.demo.entities.Customer;

/**
 * @author Mihai-Tudor Popescu
 *
 */
public interface CustomerService {
	public List<Customer> getCustomers();
	
	public void saveCustomer(Customer customer);
	
	public Customer getCustomer(int id);
	
	public void deleteCustomer(int id);
}
