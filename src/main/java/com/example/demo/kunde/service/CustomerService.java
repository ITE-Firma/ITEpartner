package com.example.demo.kunde.service;

import com.example.demo.kunde.model.Customer;

import com.example.demo.kunde.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) throws Exception {
        return customerRepository.findById(id).orElseThrow(() -> new Exception());
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }


    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }


    public void updateCustomer(Long id, Customer customer) throws Exception {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new Exception("Customer not found with id: " + id));
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());
        customerRepository.save(existingCustomer);
    }
}