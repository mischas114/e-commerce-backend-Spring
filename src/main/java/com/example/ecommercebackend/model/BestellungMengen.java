package com.example.ecommercebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Diese Klasse repräsentiert die Mengen der Produkte in einer Bestellung im E-Commerce-System.
 * Es handelt sich um eine JPA-Entitätenklasse, die der Tabelle "bestellung_mengen" in der Datenbank zugeordnet ist.
 */
@Getter
@Setter
@Entity
@Table(name = "bestellung_mengen")
public class BestellungMengen {
    /**
     * Die eindeutige Kennung der Mengen.
     * Sie wird automatisch generiert und ist der Primärschlüssel in der Tabelle "bestellung_mengen".
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Das Produkt, das in der Bestellung enthalten ist.
     * Es handelt sich um eine ManyToOne-Beziehung, da ein Produkt in mehreren Mengen enthalten sein kann.
     * Es wird durch die Spalte "produkt_id" in der Tabelle "bestellung_mengen" abgebildet.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "produkt_id", nullable = false)
    private Produkt produkt;

    /**
     * Die Menge des Produkts in der Bestellung.
     * Es handelt sich um ein Pflichtfeld.
     */
    @Column(name = "menge", nullable = false)
    private Integer menge;

    /**
     * Die Bestellung, zu der die Mengen gehören.
     * Es handelt sich um eine ManyToOne-Beziehung, da eine Bestellung mehrere Mengen haben kann.
     * Es wird durch die Spalte "bestellung_id" in der Tabelle "bestellung_mengen" abgebildet.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "bestellung_id", nullable = false)
    private Bestellung bestellung;
}