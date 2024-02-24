package com.example.ecommercebackend.api.controller.auth;

import com.example.ecommercebackend.api.model.RegistrationBody;
import com.example.ecommercebackend.exception.UserExistiertBereitsException;
import com.example.ecommercebackend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}