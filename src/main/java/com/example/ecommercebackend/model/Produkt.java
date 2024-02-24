package com.example.ecommercebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Diese Klasse repräsentiert ein Produkt im E-Commerce-System.
 * Es handelt sich um eine JPA-Entitätenklasse, die der Tabelle "produkt" in der Datenbank zugeordnet ist.
 */
@Getter
@Setter
@Entity
@Table(name = "produkt")
public class Produkt {
    /**
     * Die eindeutige Kennung des Produkts.
     * Sie wird automatisch generiert und ist der Primärschlüssel in der Tabelle "produkt".
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Der Name des Produkts.
     * Es handelt sich um ein Pflichtfeld und muss eindeutig sein.
     */
    @Column(name = "name", nullable = false, unique = true)
    private String  name;

    /**
     * Die kurze Beschreibung des Produkts.
     * Es handelt sich um ein Pflichtfeld.
     */
    @Column(name = "kurze_beschreibung", nullable = false)
    private String kurzeBeschreibung;

    /**
     * Die lange Beschreibung des Produkts.
     * Es kann bis zu 1000 Zeichen lang sein.
     */
    @Column(name = "lange_beschreibung", length = 1000)
    private String langeBeschreibung;

    /**
     * Der Preis des Produkts.
     * Es handelt sich um ein Pflichtfeld.
     */
    @Column(name = "preis", nullable = false)
    private double preis;

    /**
     * Das Inventar, das mit dem Produkt verbunden ist.
     * Es handelt sich um eine Eins-zu-Eins-Beziehung, da jedes Produkt einem eindeutigen Inventar entspricht.
     * Wenn ein Produkt entfernt wird, wird auch sein Inventar entfernt.
     */
    @OneToOne(mappedBy = "produkt", cascade = CascadeType.REMOVE, optional = false, orphanRemoval = true)
    private Inventar inventar;
}