/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
            name  = "Article.delById",
            query = "DELETE FROM Article a WHERE a.id = :id "
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
    private String category;
    
    @Temporal(TemporalType.DATE)
    private Date auctionEnd;
    
    public Article(){
        
    }
    
    public Article(User user, String n, String d, double p, String c, Date aE){
        this.user = user;
        this.name = n;
        this.description = d;
        this.price = p;
        this.category = c;
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
    
    public String getCategory(){
        return this.category;
    }
    
    public void setCategory(String c){
        this.category = c;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
