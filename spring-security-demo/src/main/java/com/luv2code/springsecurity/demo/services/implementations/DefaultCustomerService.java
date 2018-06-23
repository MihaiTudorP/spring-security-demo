/**
 * 
 */
package com.luv2code.springsecurity.demo.services.implementations;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springsecurity.demo.entities.Customer;
import com.luv2code.springsecurity.demo.repositories.CustomerRepository;
import com.luv2code.springsecurity.demo.services.CustomerService;

/**
 * @author Mihai-Tudor Popescu
 *
 */
@Service
public class DefaultCustomerService implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	/* (non-Javadoc)
	 * @see com.luv2code.springsecurity.demo.services.CustomerService#getCustomers()
	 */
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.luv2code.springsecurity.demo.services.CustomerService#saveCustomer(com.luv2code.springsecurity.demo.entities.Customer)
	 */
	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}

	/* (non-Javadoc)
	 * @see com.luv2code.springsecurity.demo.services.CustomerService#getCustomer(int)
	 */
	@Override
	@Transactional
	public Customer getCustomer(int id) {
		return customerRepository.getOne(id);
	}

	/* (non-Javadoc)
	 * @see com.luv2code.springsecurity.demo.services.CustomerService#deleteCustomer(int)
	 */
	@Override
	@Transactional
	public void deleteCustomer(int id) {
		customerRepository.deleteById(id);
	}

}
