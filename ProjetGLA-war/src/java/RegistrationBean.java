
import authentication.BCrypt;
import authentication.RegistrationManager;
import authentication.RegistrationManagerBean;
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

@Named(value = "registrationBean")
@RequestScoped
public class RegistrationBean {
    
    @EJB
    private RegistrationManager registrationBean;
    
    private String login, password, conf, erreur, nom, prenom, adresse, iban;

    public RegistrationBean() {
        this.login = "";
        this.password = "";
        this.conf = "";
        this.erreur = "";
        this.nom = "";
        this.prenom = "";
        this.adresse = "";
        this.iban = "";
    }

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
    
    public String getErreur() {
        return erreur;
    }
    
    public String getConf(){
        return conf;
    }
    
    public void setConf(String conf) {
        this.conf = conf;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
    
    
    
    public String registerLogin() {
        if(this.password.equals(this.conf) ){
            User u = this.registrationBean.register(this.login, 
                    BCrypt.hashpw(this.password, BCrypt.gensalt(12)),
                    this.nom, this.prenom, this.adresse, this.iban);
            return "index";
        }else{
            erreur = "!!!!!!!!!!!!!!!!!! erreur !!!!!!!!!!!!!!!!!!";
            return null;
        }
    }
}
