package com.example.demo.kunde.email;

import org.springframework.scheduling.annotation.Async;

public interface EmailSender {
    void send(String to, String email);
    void sendFeedback( String subject ,String email);

    @Async
    void send(String to, String subject, String email);
}