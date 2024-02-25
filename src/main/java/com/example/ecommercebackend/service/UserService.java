package com.example.ecommercebackend.service;

import com.example.ecommercebackend.api.model.LoginBody;
import com.example.ecommercebackend.api.model.RegistrationBody;
import com.example.ecommercebackend.exception.InkorrektesPasswortException;
import com.example.ecommercebackend.exception.UserExistiertBereitsException;
import com.example.ecommercebackend.model.LokalerUser;
import com.example.ecommercebackend.model.dao.LokalerUserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Ein Service, der die Registrierung von Benutzern verwaltet.
 */
@Service
@RequiredArgsConstructor
public class UserService {
    /** Das LokalerUserDAO. */
    private final LokalerUserDAO lokalerUserDAO;
    /** Der EncryptionService. */
    private final EncryptionService encryptionService;
    /** Der JWTService. */
    private final JWTService jwtService;
    /**
     * Versucht, einen Benutzer mit den bereitgestellten Informationen zu registrieren. Wenn ein Benutzer mit den bereitgestellten Informationen bereits existiert, wird eine UserExistiertBereitsException geworfen.
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
        user.setPasswort(encryptionService.encryptPasswort(registrationBody.getPasswort()));
        lokalerUserDAO.save(user);
    }
    /**
     * Versucht, einen Benutzer mit den bereitgestellten Informationen einzuloggen. Wenn ein Benutzer mit den bereitgestellten Informationen existiert, wird ein JWT generiert und zurückgegeben.
     *
     * @param loginBody Die Logininformationen.
     * @return Der generierte JWT.
     */
    public String loginUser(LoginBody loginBody) {
        Optional<LokalerUser> opUser = lokalerUserDAO.findByUsernameIgnoreCase(loginBody.getUsername());
        if(opUser.isPresent()){
            LokalerUser user = opUser.get();
            if(encryptionService.checkPasswort(loginBody.getPassword(), user.getPasswort())){
                return jwtService.generateJWT(user);
            } else {
                throw new InkorrektesPasswortException("Passwort ist falsch!");
            }
        } else {
            throw new UsernameNotFoundException("User mit dem Name " + loginBody.getUsername() + " wurde nicht gefunden");        }
    }
}
