package com.tugcegencer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tugcegencer.repository.CustomerRepository;
import com.tugcegencer.repository.OrderRepository;

@RestController
@RequestMapping("/api")
public class OrderController {
	
    @Autowired
    OrderRepository orderRepository;
    CustomerRepository customerRepository;

}
