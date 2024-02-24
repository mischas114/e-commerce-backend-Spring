package com.example.ecommercebackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert einen User im E-Commerce-System.
 * Es handelt sich um eine JPA-Entitätenklasse, die der Tabelle "user" in der Datenbank zugeordnet ist.
 */
@Getter
@Setter
@Entity
@Table(name = "lokaler_user")
public class LokalerUser {
    /**
     * Die eindeutige Kennung des Users.
     * Sie wird automatisch generiert und ist der Primärschlüssel in der Tabelle "user".
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Der username des Users.
     * Es handelt sich um ein Pflichtfeld und muss eindeutig sein.
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    /**
     * Das Passwort des Users.
     * Es handelt sich um ein Pflichtfeld und kann bis zu 1000 Zeichen lang sein.
     */
    @Column(name = "passwort", nullable = false, length = 1000)
    private String passwort;
    /**
     * Die E-Mail-Adresse des Users.
     * Es handelt sich um ein Pflichtfeld, muss eindeutig sein und kann bis zu 320 Zeichen lang sein.
     * Es wird durch die @E-Mail-Annotation validiert.
     */
    @Column(name = "email", nullable = false, unique = true, length = 320)
    private String email;
    /**
     * Der Vorname des Users.
     * Es handelt sich um ein Pflichtfeld.
     */
    @Column(name = "vorname", nullable = false)
    private String vorname;
    /**
     * Der Nachname des Users.
     * Es handelt sich um ein Pflichtfeld.
     */
    @Column(name = "nachname", nullable = false)
    private String nachname;
    /**
     * Die Adressen des Users.
     * Es handelt sich um eine Eins-zu-Viele-Beziehung, da ein user mehrere Adressen haben kann.
     * Wenn ein user entfernt wird, werden auch seine Adressen entfernt.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Adresse> adressen = new ArrayList<>();
}