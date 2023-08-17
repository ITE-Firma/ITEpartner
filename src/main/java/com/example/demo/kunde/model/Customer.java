package com.example.demo.kunde.model;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName= ""; // Set default value to empty string;
    @Column(nullable = false)
    private String lastName = ""; // Set default value to empty string
    @Column(nullable = false)
    private String email = ""; // Set default value to empty string
    @Column(nullable = false)
    private String phoneNumber = ""; // Set default value to empty string
    @Column(name = "message", length = 1000) // erhöht die maximale Länge des Felds auf 1000 Zeichen
    private String message;
    @Column(nullable = false)
    private String interest = ""; // Set default value to empty string
    @Column(nullable = false)
    private String company = ""; // Set default value to empty string
    @Column(nullable = false)
    private String anrede = ""; // Set default value to empty string


    // Constructors
    public Customer() {}

    public Customer( String anrede,String firstName, String lastName, String email, String phoneNumber, String message, String interest, String company) {
        this.anrede=anrede;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.interest = interest;
        this.company = company;
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}