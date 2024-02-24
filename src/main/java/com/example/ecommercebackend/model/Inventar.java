package com.example.ecommercebackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/**
 * Diese Klasse repräsentiert ein Inventar im E-Commerce-System.
 * Es handelt sich um eine JPA-Entitätenklasse, die der Tabelle "inventar" in der Datenbank zugeordnet ist.
 */
@Getter
@Setter
@Entity
@Table(name = "inventar")
public class Inventar {
    /**
     * Die eindeutige Kennung des Inventars.
     * Sie wird automatisch generiert und ist der Primärschlüssel in der Tabelle "inventar".
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Das Produkt, das mit dem Inventar verbunden ist.
     * Es handelt sich um eine Eins-zu-Eins-Beziehung, da jedes Inventar einem eindeutigen Produkt entspricht.
     * Die Beziehung wird durch die eindeutige Einschränkung auf der Join-Spalte durchgesetzt.
     */
    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(nullable = false, unique = true)
    private Produkt produkt;
}