package com.example.demo.kunde.Controller;

import com.example.demo.kunde.model.Customer;
import com.example.demo.kunde.model.Feedback;
import com.example.demo.kunde.service.CustomerService;

import com.example.demo.kunde.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("customer")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        List<Feedback> Feedbacks = feedbackService.getAllFeedbacks();
        model.addAttribute("feedbacks", Feedbacks);
        model.addAttribute("customers", customers);
        return "customer-list";
    }
    @GetMapping("submit-customer")
    public String getAllCustomers1(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        List<Feedback> Feedbacks = feedbackService.getAllFeedbacks();
        model.addAttribute("customers", customers);
        model.addAttribute("feedbacks", Feedbacks);

        return "customer-list";
    }
    @GetMapping("user/{id}")
    public String getCustomerById(@PathVariable Long id, Model model) throws Exception {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("customer", customer);
        return "customer-detail";
    }
    @PutMapping("user/{id}")
    public String updateCustomer(@PathVariable Long id, @RequestBody Customer customer) throws Exception {
        customerService.updateCustomer(id, customer);
        return "redirect:/";
    }

    @DeleteMapping("user/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/";
    }

}