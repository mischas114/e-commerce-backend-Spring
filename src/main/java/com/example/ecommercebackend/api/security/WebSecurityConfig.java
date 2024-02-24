package com.example.ecommercebackend.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Diese Klasse ist verantwortlich für die Konfiguration der Web-Sicherheit.
 */
@Configuration
public class WebSecurityConfig {

    /**
     * Diese Methode wird verwendet, um die Sicherheitsfilterkette zu konfigurieren.
     * Sie deaktiviert den CSRF- und CORS-Schutz und erlaubt alle Anfragen.
     *
     * @param http eine Instanz von HttpSecurity, die zum Aufbau der Sicherheitsfilterkette verwendet wird
     * @return die erstellte Sicherheitsfilterkette
     * @throws Exception wenn während der Konfiguration ein Fehler auftritt
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Deaktiviere CSRF-Schutz
        http.csrf(AbstractHttpConfigurer::disable);
        // Deaktiviere CORS-Schutz
        http.cors(AbstractHttpConfigurer::disable);
        // Erlaube alle Anfragen
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        // Erstelle und gebe die Sicherheitsfilterkette zurück
        return http.build();
    }
}