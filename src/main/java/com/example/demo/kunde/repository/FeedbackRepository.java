package com.example.demo.kunde.repository;


import com.example.demo.kunde.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// DAO class
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
