/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Benoît
 */
@Entity
@Table(name= "PANIER")
@NamedQueries ({
    @NamedQuery(
            name  = "Panier.findArticles",
            query = "SELECT p FROM Panier p "
                  + "WHERE p.user_UserId = :id" 
    ),
    @NamedQuery(
            name  = "Panier.find",
            query = "SELECT p FROM Panier p "
                  + "WHERE p.user_UserId = :id" 
    )
})
public class Panier implements Serializable{
    @Id  
    private long user_UserId;
    
    private List<Long> idArticles;
    private double prix;
    
    
    public Panier(){
        this.idArticles = new ArrayList<Long>();
    }
    
    public Panier(long idUser, List<Long> idArticles, double prix){
        this.user_UserId = idUser;
        this.idArticles = idArticles;
        this.prix = prix;
    }

    public long getUser_UserId() {
        return user_UserId;
    }

    public void setUser_UserId(long user_UserId) {
        this.user_UserId = user_UserId;
    }

    public List<Long> getIdArticles() {
        return idArticles;
    }

    public void setIdArticles(List<Long> idArticles) {
        this.idArticles = idArticles;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    public void addArticle(long idArticle){
        this.idArticles.add(idArticle);
    }
    
    public void removeArticle(long idArticle){
        this.idArticles.remove(idArticle);
    }
    
    
}
