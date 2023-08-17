package com.example.demo.kunde.model;
import javax.persistence.*;

@Entity
@Table(name = "Feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "message", length = 1000) // erhöht die maximale Länge des Felds auf 1000 Zeichen
    private String message;

    @Column(nullable = false)
    private String subject = ""; // Set default value to empty string
    public Feedback(Long id, String message, String supject) {
        this.id = id;
        this.message = message;
        this.subject = supject;
    }
    public Feedback() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSubject(String supject) {
        this.subject = supject;
    }
}
