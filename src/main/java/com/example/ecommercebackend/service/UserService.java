package com.example.ecommercebackend.service;

import com.example.ecommercebackend.api.model.RegistrationBody;
import com.example.ecommercebackend.exception.UserExistiertBereitsException;
import com.example.ecommercebackend.model.LokalerUser;
import com.example.ecommercebackend.model.dao.LokalerUserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    /** Das LokalerUserDAO. */
    private final LokalerUserDAO lokalerUserDAO;

    /**
     * Versucht, einen Benutzer mit den bereitgestellten Informationen zu registrieren.
     *
     * @param registrationBody Die Registrierungsinformationen.
     * @throws UserExistiertBereitsException Wird geworfen, wenn bereits ein Benutzer mit den gegebenen Informationen existiert.
     */
    public void registerUser(RegistrationBody registrationBody) throws UserExistiertBereitsException {
        if(lokalerUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
                || lokalerUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
            throw new UserExistiertBereitsException();
        }
        LokalerUser user = new LokalerUser();
        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setVorname(registrationBody.getVorname());
        user.setNachname(registrationBody.getNachname());
        //todo: Passwort verschlüsseln
        user.setPasswort(registrationBody.getPasswort());
        lokalerUserDAO.save(user);
    }
}
