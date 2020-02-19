/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author quent
 */
@Stateless
public class RegistrationManagerBean implements RegistrationManager {
    
    @PersistenceContext(unitName = "truc-PU")
    private EntityManager em;
    
    @Override
    public User register(String login, String mdp, String nom, String prenom, String adresse, String iban) {
        User u = new User();
        u.setLogin(login);
        u.setPassword(mdp);
        u.setNom(nom);
        u.setPrenom(prenom);
        u.setAdresse(adresse);
        u.setIban(iban);
        u.setCancelCount(0);
        em.persist(u);    
        return u;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
}
