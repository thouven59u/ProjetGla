/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import entities.User;
import javax.ejb.Local;

/**
 *
 * @author quent
 */
@Local
public interface AuthenticationManager {
    public User authenticate(String login, String mdp);
    
    public void logOut();
    public User getUser();
}
