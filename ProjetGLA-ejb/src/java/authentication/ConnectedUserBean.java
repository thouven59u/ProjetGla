/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import entities.User;
import javax.ejb.Stateful;

/**
 *
 * @author quent
 */
@Stateful
public class ConnectedUserBean implements ConnectedUser {
    
    private User usr;
    
    @Override
    public void setUser(User u) {
        this.usr = u;
    }

    @Override
    public User getUser() {
        return this.usr;
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
