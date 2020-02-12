
import authentication.AuthenticationManager;
import authentication.ConnectedUser;
import entities.User;
import javax.annotation.PostConstruct;
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
@Named(value = "testBean")
@RequestScoped
public class TestBean {
    
    @EJB
    private AuthenticationManager cUsr;
    
    private User u;
    
    public TestBean() {}
    
    @PostConstruct
    public void init( ) {
        u = this.cUsr.getUser();
        System.out.println("INFO : " + u);
    }
    public String logout() {
        return "index";
    }
    
    public String getUser() { 
        return u.toString();
        
    }
    
    public String getLogin() {
        return u.getLogin();
    }
    
     
}
