package com.example.ecommercebackend.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * Service-Klasse zur Verschlüsselung von Passwörtern mittels Bcrypt.
 * Diese Klasse ist als eigenständiger Service konzipiert, um eine einfache Aktualisierung oder Austauschbarkeit zu ermöglichen.
 * Sie verwendet Field Injection, um die Anzahl der Konstruktorparameter zu minimieren und die Austauschbarkeit zu erleichtern.
 */
@Service
public class EncryptionService {
    /**
     * Die Anzahl der Runden, die Bcrypt für die Passwortverschlüsselung verwendet.
     * Ein höherer Wert bedeutet eine stärkere Verschlüsselung, erfordert aber mehr Rechenzeit.
     * Der Wert wird aus der Anwendungseigenschaft `encryption.salt.rounds` in der Datei `application.properties` injiziert.
     */
    @Value("${encryption.salt.rounds}")
    private int saltRounds;
    private String salt;

    /**
     * Diese Methode wird nach der Initialisierung der Klasse aufgerufen und generiert das Salz für die Verschlüsselung.
     * Das generierte Salz wird für die Verschlüsselung aller Passwörter verwendet.
     */
    @PostConstruct
    public void postConstruct() {
        salt = BCrypt.gensalt(saltRounds);
    }

    /**
     * Verschlüsselt ein gegebenes Passwort mit Bcrypt und dem generierten Salz.
     * @param passwort das zu verschlüsselnde Passwort
     * @return das verschlüsselte Passwort
     */
    public String encryptPasswort(String passwort) {
        return BCrypt.hashpw(passwort, salt);
    }

    /**
     * Überprüft ein gegebenes Passwort gegen ein verschlüsseltes Passwort (Hash).
     * @param passwort das zu überprüfende Passwort
     * @param hash das verschlüsselte Passwort, gegen das geprüft wird
     * @return true, wenn das Passwort mit dem Hash übereinstimmt, andernfalls false
     */
    public boolean checkPasswort(String passwort, String hash) {
        return BCrypt.checkpw(passwort, hash);
    }
}