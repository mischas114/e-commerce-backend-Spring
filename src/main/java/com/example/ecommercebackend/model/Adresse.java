package com.example.ecommercebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Diese Klasse repräsentiert eine Adresse im E-Commerce-System.
 * Es handelt sich um eine JPA-Entitätenklasse, die der Tabelle "adresse" in der Datenbank zugeordnet ist.
 */
@Getter
@Setter
@Entity
@Table(name = "adresse")
public class Adresse {
    /**
     * Die eindeutige Kennung der Adresse.
     * Sie wird automatisch generiert und ist der Primärschlüssel in der Tabelle "adresse".
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Die erste Zeile der Adresse.
     * Es handelt sich um ein Pflichtfeld, das bis zu 512 Zeichen lang sein kann.
     */
    @Column(name = "address_zeile_1", nullable = false, length = 512)
    private String addressZeile1;
    /**
     * Die zweite Zeile der Adresse.
     * Es handelt sich um ein optionales Feld, das bis zu 512 Zeichen lang sein kann.
     */
    @Column(name = "address_zeile_2", length = 512)
    private String addressZeile2;
    /**
     * Das Land der Adresse.
     * Es handelt sich um ein Pflichtfeld, das bis zu 75 Zeichen lang sein kann.
     */
    @Column(name = "stadt", nullable = false, length = 75)
    private String stadt;
    /**
     * Der User, der die Adresse besitzt.
     * Es handelt sich um eine ManyToOne-Beziehung, da ein User mehrere Adressen haben kann.
     * Es wird durch die Spalte "user_id" in der Tabelle "adresse" abgebildet.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LokalerUser user;
}