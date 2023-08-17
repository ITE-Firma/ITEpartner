package com.example.demo.kunde.service;



import com.example.demo.kunde.model.Customer;
import com.example.demo.kunde.model.Feedback;
import com.example.demo.kunde.repository.CustomerRepository;
import com.example.demo.kunde.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
    public Feedback saveFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }
    public Feedback getFeedbackById(Long id) throws Exception {
        return feedbackRepository.findById(id).orElseThrow(() -> new Exception());
    }
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
    public void updateFeedback(Long id, Feedback feedback) throws Exception {
        Feedback feedback1 = feedbackRepository.findById(id)
                .orElseThrow(() -> new Exception("Customer not found with id: " + id));
        feedbackRepository.save(feedback1);
    }

}
