package com.example.demo.kunde.regestrieren.token;


import com.example.demo.kunde.email.EmailSender;
import com.example.demo.kunde.model.User;
import com.example.demo.kunde.model.UserRole;
import com.example.demo.kunde.regestrieren.EmailValidator;
import com.example.demo.kunde.regestrieren.RegistrationRequest;
import com.example.demo.kunde.repository.UserRepository;
import com.example.demo.kunde.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.time.LocalDateTime;

@Service
//@AllArgsConstructor
public class RegistrationService
{
    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;
    private final UserRepository userRepository;

    @Autowired
    public RegistrationService(UserService userService, EmailValidator emailValidator, ConfirmationTokenService confirmationTokenService, EmailSender emailSender, UserRepository userRepository) {
        this.userService = userService;
        this.emailValidator = emailValidator;
        this.confirmationTokenService = confirmationTokenService;
        this.emailSender = emailSender;
        this.userRepository = userRepository;
    }
    public String registerAdmin(RegistrationRequest request, HttpServletRequest servletRequest)
    {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail)
        {
            throw new IllegalStateException("email not valid");
        }
        User appUser = new User(
                request.getUserName(),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getTitel(),
                request.getAdresse(),
                request.getPlz(),
                request.getStadt(),
                request.getTelefonnummer(),
                request.getGeburtsdatum(),
                request.getGeburtsort(),
                UserRole.USER
        );

        String token = userService.signUpUser(appUser);

        String serverName = servletRequest.getServerName();
        int serverPort = servletRequest.getServerPort();
        String link = "http://" + serverName + ":" + serverPort + "/api/v1/user/confirm?token=" + token;

        emailSender.send(
                request.getEmail(),
                buildEmail(
                        request.getUserName(),
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        request.getTitel(),
                        request.getAdresse(),
                        request.getPlz(),
                        request.getStadt(),
                        request.getTelefonnummer(),
                        request.getGeburtsdatum(),
                        request.getGeburtsort(),
                        link
                )
        );

        return token;
    }
    @Transactional
    public String confirmToken(String token)
    {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null)
        {
            throw new IllegalStateException("email already confirmed");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        if (expiredAt.isBefore(LocalDateTime.now()))
        {
            throw new IllegalStateException("token expired");
        }
        confirmationTokenService.setConfirmedAt(token);
        userService.enableUser(
                confirmationToken.getUser().getEmail());
        return "confirmed";
    }
    private String buildEmail(String userName,
                              String name,
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
                              String link)
    {
        String htmlTemplate = "";
        try {
            InputStream inputStream = getClass().getResourceAsStream("/templates/email_template.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null)
            {
                htmlTemplate += line;
            }
            reader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        htmlTemplate = htmlTemplate.replace("{{name}}", name);
        htmlTemplate = htmlTemplate.replace("{{link}}", link);
        htmlTemplate = htmlTemplate.replace("{{Email}}", email);
        htmlTemplate = htmlTemplate.replace("{{Adresse}}", adresse);
        htmlTemplate = htmlTemplate.replace("{{Plz}}", plz);
        htmlTemplate = htmlTemplate.replace("{{Stadt}}", stadt);
        htmlTemplate = htmlTemplate.replace("{{Telefonnummer}}", telefonnummer);
        htmlTemplate = htmlTemplate.replace("{{Geburtsdatum}}", geburtsdatum);
        htmlTemplate = htmlTemplate.replace("{{Geburtsort}}", geburtsort);
        htmlTemplate = htmlTemplate.replace("{{Titel}}", titel);
        htmlTemplate = htmlTemplate.replace("{{LastName}}", lastName);
        htmlTemplate = htmlTemplate.replace("{{userName}}", userName);

        return htmlTemplate;
    }
    private String buildEmail(String userName,
                              String name,
                              String lastName,
                              String email,
                              String password,
                              String titel,
                              String adresse,
                              String plz,
                              String stadt,
                              String telefonnummer,
                              String geburtsdatum,
                              String link)
    {
        String htmlTemplate = "";
        try {
            InputStream inputStream = getClass().getResourceAsStream("/templates/email_template.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null)
            {
                htmlTemplate += line;
            }
            reader.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        htmlTemplate = htmlTemplate.replace("{{name}}", name);
        htmlTemplate = htmlTemplate.replace("{{link}}", link);
        htmlTemplate = htmlTemplate.replace("{{Email}}", email);
        htmlTemplate = htmlTemplate.replace("{{Adresse}}", adresse);
        htmlTemplate = htmlTemplate.replace("{{Plz}}", plz);
        htmlTemplate = htmlTemplate.replace("{{Stadt}}", stadt);
        htmlTemplate = htmlTemplate.replace("{{Telefonnummer}}", telefonnummer);
        htmlTemplate = htmlTemplate.replace("{{Geburtsdatum}}", geburtsdatum);
        htmlTemplate = htmlTemplate.replace("{{Titel}}", titel);
        htmlTemplate = htmlTemplate.replace("{{LastName}}", lastName);
        htmlTemplate = htmlTemplate.replace("{{userName}}", userName);
        return htmlTemplate;
    }
    public String getOperatingSystemFromServletRequest(HttpServletRequest servletRequest)
    {
        String userAgent = servletRequest.getHeader("User-Agent");
        return getOperatingSystemFromUserAgent(userAgent);
    }
    public String getOperatingSystemFromUserAgent(String userAgent)
    {
        String operatingSystem = "Unknown";

        if (userAgent != null)
        {
            if (userAgent.contains("Windows"))
            {
                operatingSystem = "Windows";
            } else if (userAgent.contains("Mac"))
            {
                operatingSystem = "Mac";
            } else if (userAgent.contains("Linux"))
            {
                operatingSystem = "Linux";
            } else if (userAgent.contains("Android"))
            {
                operatingSystem = "Android";
            } else if (userAgent.contains("iOS"))
            {
                operatingSystem = "iOS";
            }
        }
        return operatingSystem;
    }
}