package com.example.demo.kunde.model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "Bilder")
public class Bilder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] logo;
    @Lob
    private byte[] welcome;
    @Lob
    private byte[] aboutus;
    @Lob
    private byte[] referenzen;
    @Lob
    private byte[] auditierung;
    @Lob
    private byte[] akustik;
    @Lob
    private byte[] beratung;
    @Lob
    private byte[] engineering;
    @Lob
    private byte[] hust;
    @Lob
    private byte[] programierung;
    @Lob
    private byte[] projecte;
    @Lob
    private byte[] qus;
    @Lob
    private byte[] shuk;
    public Bilder() {

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

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public byte[] getWelcome() {
        return welcome;
    }

    public void setWelcome(byte[] welcome) {
        this.welcome = welcome;
    }

    public byte[] getAboutus() {
        return aboutus;
    }

    public void setAboutus(byte[] aboutus) {
        this.aboutus = aboutus;
    }

    public byte[] getReferenzen() {
        return referenzen;
    }

    public void setReferenzen(byte[] referenzen) {
        this.referenzen = referenzen;
    }

    public byte[] getAuditierung() {
        return auditierung;
    }

    public void setAuditierung(byte[] auditierung) {
        this.auditierung = auditierung;
    }

    public byte[] getAkustik() {
        return akustik;
    }

    public void setAkustik(byte[] akustik) {
        this.akustik = akustik;
    }

    public byte[] getBeratung() {
        return beratung;
    }

    public void setBeratung(byte[] beratung) {
        this.beratung = beratung;
    }

    public byte[] getEngineering() {
        return engineering;
    }

    public void setEngineering(byte[] engineering) {
        this.engineering = engineering;
    }

    public byte[] getHust() {
        return hust;
    }

    public void setHust(byte[] hust) {
        this.hust = hust;
    }

    public byte[] getProgramierung() {
        return programierung;
    }

    public void setProgramierung(byte[] programierung) {
        this.programierung = programierung;
    }

    public byte[] getProjecte() {
        return projecte;
    }

    public void setProjecte(byte[] projecte) {
        this.projecte = projecte;
    }

    public byte[] getQus() {
        return qus;
    }

    public void setQus(byte[] qus) {
        this.qus = qus;
    }

    public byte[] getShuk() {
        return shuk;
    }

    public void setShuk(byte[] shuk) {
        this.shuk = shuk;
    }

    @Override
    public String toString() {
        return "Bilder{" +
                "id=" + id +
                ", logo=" + Arrays.toString(logo) +
                ", welcome=" + getWelcome() +
                ", aboutus=" + Arrays.toString(aboutus) +
                ", referenzen=" + Arrays.toString(referenzen) +
                ", auditierung=" + Arrays.toString(auditierung) +
                ", akustik=" + Arrays.toString(akustik) +
                ", beratung=" + Arrays.toString(beratung) +
                ", engineering=" + Arrays.toString(engineering) +
                ", hust=" + Arrays.toString(hust) +
                ", programierung=" + Arrays.toString(programierung) +
                ", projecte=" + Arrays.toString(projecte) +
                ", qus=" + Arrays.toString(qus) +
                ", shuk=" + Arrays.toString(shuk) +
                '}';
    }
}