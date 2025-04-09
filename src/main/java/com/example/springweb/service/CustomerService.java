package com.example.springweb.service;

import com.example.springweb.entities.Customers;
import com.example.springweb.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customers> getAllCustomer() {
        return customerRepository.findAll();
    }
}
