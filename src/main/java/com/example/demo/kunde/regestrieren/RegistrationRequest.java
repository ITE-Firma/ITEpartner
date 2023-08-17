package com.example.demo.kunde.regestrieren;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String userName;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String titel;
    private final String adresse;
    private final String plz;
    private final String stadt;
    private final String telefonnummer;
    private final String geburtsdatum;
    private final String geburtsort;
}
