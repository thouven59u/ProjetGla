
import authentication.AuthenticationManager;
import entities.User;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author quent
 */
@Named(value = "authBean")
@RequestScoped
public class AuthBean {
    
    @EJB
    private AuthenticationManager authManager;
    
    private String login, password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String authenticateUser(){
        User u = authManager.authenticate(this.login, this.password);
        //System.out.println("INFOOO"+u+ "\n\n\n");
        return "test";
    }
    
}
