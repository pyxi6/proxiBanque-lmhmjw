package com.formation.proxibanque.lmhmjw.controller;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formation.proxibanque.lmhmjw.entity.Adresse;
import com.formation.proxibanque.lmhmjw.entity.CompteCourant;
import com.formation.proxibanque.lmhmjw.entity.Conseiller;
import com.formation.proxibanque.lmhmjw.entity.Customer;
import com.formation.proxibanque.lmhmjw.entity.enums.CompteStatus;
import com.formation.proxibanque.lmhmjw.repository.CustomerRepository;
import com.formation.proxibanque.lmhmjw.service.CustomerServiceImpl;

@RestController
public class CustomerRestController {

	@Autowired
	private CustomerServiceImpl customerServiceImpl;
	
	@Autowired
	private CustomerRepository customerRepository;

	public CustomerRestController(CustomerServiceImpl customerServiceImpl) {
		super();
		this.customerServiceImpl = customerServiceImpl;
	}
	
	Adresse adresse1 = new Adresse("1 rue Jamin", "93210", "Saint-Denis");
	Adresse adresse2 = new Adresse("2 rue de la liberté", "93654", "Boulogne");
	Adresse adresse3 = new Adresse("9 rue Hype", "32654", "Pzerlimpin");


	@PostConstruct
	public void dateLoaderCustomer(){
		List<Customer> listeCustomers= List.of(
				new Customer("Jean", "Neige", "123456", adresse1),
				new Customer("Marc", "Boue", "12348996", adresse2),
				new Customer("Karim", "Benhamou", "1264545", adresse3)
				
   		);
		
		customerRepository.saveAll(listeCustomers);
	}

	@GetMapping(path = "/customers")
	public List<Customer> listAllCustomers() {
		return customerServiceImpl.listAllCustomersService();
	}

	@GetMapping(path = "/customers/{id}")
	public Customer getCustomerById(@PathVariable Long id) {
		return customerServiceImpl.getCustomerByIdService(id);
	}

	@DeleteMapping(path = "/customers/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerServiceImpl.deleteCustomerService(id);
	}

	@PostMapping(path = "/customers")
	public Customer saveCustomer(@RequestBody Customer customer) {
		return customerServiceImpl.saveCustomerService(customer);

	}

	@PutMapping(path = "/customers/{id}")
	public Customer updateCustommer(@PathVariable Long id, @RequestBody Customer customer) {
		customer.setId(id);
		return customerServiceImpl.saveCustomerService(customer);

	}
}
