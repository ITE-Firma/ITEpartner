package com.example.demo.kunde.model;

import org.springframework.ui.Model;

import javax.persistence.*;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;

@Entity
public class Projekt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] logo;

    private String name;
    @Column(name = "kurzebeschreibung", columnDefinition="VARCHAR")
    private String kurzebeschreibung;
    @Column(name = "beschreibung", columnDefinition="VARCHAR")
    private String beschreibung;
    private double status;
    private String  details;



    public Projekt() {

    }

    public Projekt(Long id, byte[] logo, String name, String beschreibung, double status, String details, String kurzebeschreibung) {
        this.id = id;
        this.logo = logo;
        this.name = name;
        this.beschreibung = beschreibung;
        this.status = status;
        this.details = details;
        this.kurzebeschreibung=kurzebeschreibung;
    }

    public Projekt(byte[] logo, String name, String beschreibung, double status, String details,String kurzebeschreibung) {
        this.logo = logo;
        this.name = name;
        this.beschreibung = beschreibung;
        this.status = status;
        this.details = details;
        this.kurzebeschreibung=kurzebeschreibung;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getLogo() {
        return logo;
    }

    public String getKurzebeschreibung() {
        return kurzebeschreibung;
    }

    public void setKurzebeschreibung(String kurzebeschreibung) {
        this.kurzebeschreibung = kurzebeschreibung;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }
    public String getEncodedLogo(Projekt projekt) {
        if (projekt.getLogo() != null) {
            byte[] logoData = projekt.getLogo();
            String imageFormat = isJpeg(logoData) ? "jpeg" : "png";
            String base64Image = Base64.getEncoder().encodeToString(logoData);
            return "data:image/" + imageFormat + ";base64," + base64Image;
        }
        return null; // or an empty string
    }
    public boolean isJpeg(byte[] imageData) {
        // JPEG-Bilder beginnen typischerweise mit den Bytes 0xFF, 0xD8
        return (imageData.length >= 2 && imageData[0] == (byte) 0xFF && imageData[1] == (byte) 0xD8);
    }
}
