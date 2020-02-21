/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author quent
 */
@Entity
@Table(name= "PROMOTIONS")
@NamedQueries ({
    @NamedQuery(
            name  = "Promotion.deleteAll",
            query = "DELETE FROM Promotion"
    ),
    @NamedQuery(
            name  = "Promotion.getAll",
            query = "SELECT p FROM Promotion p"
    ),
    @NamedQuery(
            name  = "Promotion.getByArticleId",
            query = "SELECT p FROM Promotion p WHERE p.article = :id"
    )
})
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String type;
    
    @OneToOne
    private Article article;
    
    public Promotion(){}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    
    

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", type=" + type + '}';
    }
    
    
}
