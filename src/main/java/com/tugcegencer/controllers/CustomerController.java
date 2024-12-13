package com.tugcegencer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tugcegencer.baseEntity.Customer;
import com.tugcegencer.repository.CustomerRepository;

@RestController
@RequestMapping("/api")
public class CustomerController {
	  @Autowired
	    CustomerRepository customerRepository;
	  
	  @PostMapping("/addcustomer")
	    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
	        try {
	            Customer newCustomer = customerRepository.save(customer);
	            System.out.println(newCustomer);
	            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);


	        }
	        catch (Exception e) {
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

}
