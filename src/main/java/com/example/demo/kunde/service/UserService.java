package com.example.demo.kunde.service;
import com.example.demo.kunde.model.Customer;
import com.example.demo.kunde.model.User;
import com.example.demo.kunde.regestrieren.token.ConfirmationToken;
import com.example.demo.kunde.regestrieren.token.ConfirmationTokenService;
import com.example.demo.kunde.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    public String signUpUser(User user) {
        boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();
        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            throw new IllegalStateException("email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());
        user.setPassword(encodedPassword);
        User savedUser = userRepository.save(user);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                savedUser
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }

    public User updateUser(Long id, User user) {

        Optional<User> optionalAppUser = userRepository.findById(id);
        if (optionalAppUser.isPresent()) {

            User existingUser = optionalAppUser.get();

            existingUser.setUserName(user.getUsername());
            existingUser.setTitel(user.getTitel());
            existingUser.setEmail(user.getEmail());
            existingUser.setAdresse(user.getAdresse());
            existingUser.setPlz(user.getPlz());
            existingUser.setStadt(user.getStadt());
            existingUser.setTelefonnummer(user.getTelefonnummer());
            return userRepository.save(existingUser);
        } else {
            throw new IllegalArgumentException("Benutzer nicht gefunden");
        }
    }
}