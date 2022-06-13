package com.formation.proxibanque.lmhmjw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.proxibanque.lmhmjw.entity.Customer;
import com.formation.proxibanque.lmhmjw.repository.CustomerRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@GetMapping(path = "/customers")
	public List<Customer> listAllCustomers(){
		return customerRepository.findAll();
	}
	
	@GetMapping(path = "/customers/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
		return customerRepository.findById(id).get();
	}
	
	@DeleteMapping(path = "/customers/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerRepository.deleteById(id);
	}
	
	@PostMapping(path = "/customers")
	public Customer saveCustomer(@RequestBody Customer customer) {
		return customerRepository.save(customer);
		
		
	}
}
