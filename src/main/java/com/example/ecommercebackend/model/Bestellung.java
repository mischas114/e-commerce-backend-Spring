package com.example.ecommercebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse repräsentiert eine Bestellung im E-Commerce-System.
 * Es handelt sich um eine JPA-Entitätenklasse, die der Tabelle "bestellung" in der Datenbank zugeordnet ist.
 */
@Getter
@Setter
@Entity
@Table(name = "bestellung")
public class Bestellung {

    /**
     * Die eindeutige Kennung der Bestellung.
     * Sie wird automatisch generiert und ist der Primärschlüssel in der Tabelle "bestellung".
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Der user, der die Bestellung aufgegeben hat.
     * Es handelt sich um eine ManyToOne-Beziehung, da ein user mehrere Bestellungen aufgeben kann.
     * Es wird durch die Spalte "user_id" in der Tabelle "bestellung" abgebildet.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LokalerUser user;

    /**
     * Die Adresse, an die die Bestellung geliefert werden soll.
     * Es handelt sich um eine ManyToOne-Beziehung, da eine Adresse mehrere Bestellungen haben kann.
     * Es wird durch die Spalte "adresse_id" in der Tabelle "bestellung" abgebildet.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "adresse_id", nullable = false)
    private Adresse adresse;

    /**
     * Die Mengen der Produkte in der Bestellung.
     * Es handelt sich um eine Eins-zu-Viele-Beziehung, da eine Bestellung mehrere Mengen haben kann.
     * Es wird durch das Feld "bestellung" in der Klasse "BestellungMengen" abgebildet.
     * Wenn eine Bestellung entfernt wird, werden auch alle ihre Mengen entfernt.
     */
    @OneToMany(mappedBy = "bestellung", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<BestellungMengen> mengen = new ArrayList<>();
}