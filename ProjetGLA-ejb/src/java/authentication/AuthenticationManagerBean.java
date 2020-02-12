/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import entities.User;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author quent
 */
@Stateless
public class AuthenticationManagerBean implements AuthenticationManager {
    
    @PersistenceContext(unitName = "truc-PU")
    private EntityManager em;
    
    @EJB
    private ConnectedUser connectedUser;
    
    
    @Override
    public User authenticate(String login, String mdp) {
        Query q = em.createNamedQuery("User.findByLogin");
        q.setParameter("login", login);
        User u = (User) q.getSingleResult();
        if (u.getPassword().equals(mdp)) {
            this.connectedUser.setUser(u);
        }
        else {
            this.connectedUser.setUser(null);
        }
            
        return this.connectedUser.getUser();
    }
    
    @Override
    public User getUser() {
        System.out.println("INFO : JE SUIS DANS GET USER");
        return this.connectedUser.getUser();
    }

}
