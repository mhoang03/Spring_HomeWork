package com.example.springweb.controller;

import com.example.springweb.entities.Customers;
import com.example.springweb.repository.CustomerRepository;
import com.example.springweb.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public String listCustomers(Model model) {
        List<Customers> customers = customerService.getAllCustomer();
        model.addAttribute("customers", customers != null ? customers : new ArrayList<>());
        return "customers-list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("customer", new Customers());
        return "add-customer";
    }

    @PostMapping("/add")
    public String addCustomer(Customers customer) {
        customerRepository.save(customer);
        return "redirect:/customers";
    }
}
