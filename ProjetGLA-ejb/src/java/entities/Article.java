/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name= "ARTICLES")
@NamedQueries ({
    @NamedQuery(
            name  = "Article.find",
            query = "SELECT a from Article a "
                  + "WHERE a.id = :id" 
    ),
     @NamedQuery(
            name  = "Article.all",
            query = "SELECT a from Article a "
                  + "WHERE a.auctionEnd >= :today "
    ),
     @NamedQuery(
            name  = "Article.byUser",
            query = "SELECT a from Article a "
                  + "WHERE a.user = :user "
    ),
     @NamedQuery(
            name  = "Article.delById",
            query = "DELETE FROM Article a WHERE a.id = :id "
    ),
    @NamedQuery(
        name  = "Article.findByCat",
        query = "SELECT a from Article a " +
                "WHERE a.auctionEnd >= :today " +
                "AND a.categorie = :cat"
    )
})
public class Article implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    private User user;
    
    private String name;
    private String description;
    private double price;
    private String categorie;
    
    @Temporal(TemporalType.DATE)
    private Date auctionEnd;
    
    @OneToOne(mappedBy = "article", targetEntity = Promotion.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Promotion promotion;
    
    public Article(){
        
    }
    
    public Article(User user, String n, String d, double p, String c, Date aE){
        this.user = user;
        this.name = n;
        this.description = d;
        this.price = p;
        this.categorie = c;
        this.auctionEnd = aE;
    }
    
    public Long getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String n){
        this.name = n;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String d){
        this.description = d;
    }
    
    public double getPrice(){
        return this.price;
    }

    public Date getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(Date auctionEnd) {
        this.auctionEnd = auctionEnd;
    }
    
    public void setPrice(double p){
        this.price = p;
    }
    
    public String getCategorie(){
        return this.categorie;
    }
    
    public void setCategorie(String c){
        this.categorie = c;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", user=" + user + ", name=" + name + ", categorie=" + categorie + ", Promo=" + promotion +'}';
    }

    public Promotion getPromotion() {
        return promotion;
    }
    
    public boolean hasPromotion() {
        return this.promotion == null;
    }
    
}
