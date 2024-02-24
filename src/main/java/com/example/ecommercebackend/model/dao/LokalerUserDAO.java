package com.example.ecommercebackend.model.dao;

import com.example.ecommercebackend.model.LokalerUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Dieses Interface erweitert CrudRepository und bietet Methoden zum Arbeiten mit LokalerUser-Entitäten.
 * Es ermöglicht das Durchführen von CRUD-Operationen (Erstellen, Lesen, Aktualisieren, Löschen) auf LokalerUser-Entitäten.
 */
public interface LokalerUserDAO extends CrudRepository<LokalerUser, Long> {

    /**
     * Sucht einen LokalerUser anhand des Benutzernamens (case-insensitive).
     * @param username Der Benutzername des gesuchten LokalerUser.
     * @return Ein Optional, das den LokalerUser enthält, wenn ein Benutzer mit dem gegebenen Benutzernamen gefunden wurde, sonst Optional.empty().
     */
    Optional<LokalerUser>  findByUsernameIgnoreCase(String username);

    /**
     * Sucht einen LokalerUser anhand der E-Mail-Adresse (case-insensitive).
     * @param email Die E-Mail-Adresse des gesuchten LokalerUser.
     * @return Ein Optional, das den LokalerUser enthält, wenn ein Benutzer mit der gegebenen E-Mail-Adresse gefunden wurde, sonst Optional.empty().
     */
    Optional<LokalerUser> findByEmailIgnoreCase(String email);
}