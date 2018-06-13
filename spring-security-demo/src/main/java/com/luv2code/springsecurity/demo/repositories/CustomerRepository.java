/**
 * 
 */
package com.luv2code.springsecurity.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luv2code.springsecurity.demo.entities.Customer;

/**
 * @author Mihai-Tudor Popescu
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}