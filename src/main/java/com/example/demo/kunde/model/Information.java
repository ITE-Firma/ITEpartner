package com.example.demo.kunde.model;

import javax.persistence.*;

@Entity
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String info;

    public Information(Long id, String info) {
        this.id = id;
        this.info = info;
    }
    public Information( String info) {
        this.info = info;
    }
    public Information( ) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
