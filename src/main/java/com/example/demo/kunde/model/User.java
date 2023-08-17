package com.example.demo.kunde.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class User implements UserDetails
{

    @SequenceGenerator(
            name = "ItLogik_sequence",
            sequenceName = "ItLogik_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ItLogik"
    )

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String titel;
    private String adresse;
    private String plz;
    private String stadt;
    private String telefonnummer;
    private String geburtsdatum;
    private String geburtsort;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    public User(String userName,
                String firstName,
                String lastName,
                String email,
                String password,
                String titel,
                String adresse,
                String plz,
                String stadt,
                String telefonnummer,
                String geburtsdatum,
                String geburtsort,
                UserRole userRole)
    {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.titel = titel;
        this.adresse = adresse;
        this.plz = plz;
        this.stadt = stadt;
        this.telefonnummer = telefonnummer;
        this.geburtsdatum = geburtsdatum;
        this.geburtsort = geburtsort;
        this.userRole = userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(userRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}