package com.example.ecommercebackend.api.controller.auth;

import com.example.ecommercebackend.api.model.LoginBody;
import com.example.ecommercebackend.api.model.LoginResponse;
import com.example.ecommercebackend.api.model.RegistrationBody;
import com.example.ecommercebackend.exception.InkorrektesPasswortException;
import com.example.ecommercebackend.exception.UserExistiertBereitsException;
import com.example.ecommercebackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ein Controller, der die Authentifizierung von Benutzern verwaltet.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthentifizierungController {

    private final UserService userService;

    /**
     * Registriert einen neuen Benutzer mit den bereitgestellten Informationen.
     * @param registrationBody Die Registrierungsinformationen.
     * @return  HttpStatus 200, wenn die Registrierung erfolgreich war oder HttpStatus 409, wenn ein Benutzer mit den bereitgestellten Informationen bereits existiert.
     */
    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody RegistrationBody registrationBody) {
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (UserExistiertBereitsException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
        try {
            String jwt = userService.loginUser(loginBody);
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (InkorrektesPasswortException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}