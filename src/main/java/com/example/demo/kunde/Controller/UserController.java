package com.example.demo.kunde.Controller;

import com.example.demo.kunde.model.Customer;
import com.example.demo.kunde.model.Feedback;
import com.example.demo.kunde.model.User;
import com.example.demo.kunde.service.CustomerService;
import com.example.demo.kunde.service.FeedbackService;
import com.example.demo.kunde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController{
    @Autowired
    private CustomerService customerService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String showadmin(Model model) {
        System.out.println("showadmin sucess");
        List<Customer> customers = customerService.getAllCustomers();
        List<Feedback> Feedbacks = feedbackService.getAllFeedbacks();
        List<User> users= userService.getAllUsers();
        model.addAttribute("feedbacks", Feedbacks);
        model.addAttribute("customers", customers);
        model.addAttribute("users", users);
        return "AdminPortal";
    }
    @GetMapping("/admin/feedbackportal")
    public String showFeedbackPortal(Model model) {
        System.out.println("showFeedbackPortal sucess");
        List<Feedback> Feedbacks = feedbackService.getAllFeedbacks();
        model.addAttribute("feedbacks", Feedbacks);
        return "AdminFeedbackPortal";
    }
    @GetMapping("/admin/customerportal")
    public String showCustomerPortal(Model model) {
        System.out.println("showshowCustomerPortal sucess");
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "CustomerPortal";
    }
    @PostMapping("/admin/delete/feedback/{id}")
    public String deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return "redirect:/admin";
    }
    @PostMapping("/admin/delete/customer/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/admin";
    }
}