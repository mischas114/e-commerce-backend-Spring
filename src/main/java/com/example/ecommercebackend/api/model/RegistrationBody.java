package com.example.ecommercebackend.api.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Diese Klasse repräsentiert den Körper einer Registrierungsanfrage.
 * Sie enthält die notwendigen Felder für eine Benutzerregistrierung.
 * Sie verwendet Annotationen zur Validierung der Felder. Sowie Lombok für Getter und Setter.
 */
@Getter @Setter
public class RegistrationBody {
    /**
     * Der Benutzername des Benutzers.
     * Er darf nicht null oder leer sein und seine Größe muss zwischen 3 und 255 Zeichen liegen.
     */
    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private String username;

    /**
     * Die E-Mail des Benutzers.
     * Sie darf nicht null oder leer sein und sie muss eine gut geformte E-Mail-Adresse sein.
     */
    @NotNull
    @NotBlank
    @Email
    private String email;

    /**
     * Das Passwort des Benutzers.
     * Es darf nicht null oder leer sein und seine Größe muss zwischen 6 und 32 Zeichen liegen.
     * Es muss mindestens einen Großbuchstaben, einen Kleinbuchstaben und eine Ziffer enthalten.
     */
    @NotBlank
    @NotNull
    @Size(min = 6, max = 32)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Das  Passwort muss mindestens einen Großbuchstaben, einen Kleinbuchstaben und eine Zahl enthalten")
    private String passwort;

    /**
     * Der Vorname des Benutzers.
     * Er darf nicht null oder leer sein.
     */
    @NotBlank
    @NotNull
    private String vorname;

    /**
     * Der Nachname des Benutzers.
     * Er darf nicht null oder leer sein.
     */
    @NotBlank
    @NotNull
    private String nachname;
}