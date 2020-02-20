/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Benoît
 */
@Entity
@Table(name= "USERS_ARTICLES")
@NamedQueries ({
    @NamedQuery(
            name  = "Users_Articles.find",
            query = "SELECT a FROM UsersArticles a "
                  + "WHERE a.user_UserId = :uId "
                  + "AND a.articles_Id = :aId"
    ),
    @NamedQuery(
            name  = "Users_Articles.findArticle",
            query = "SELECT a FROM UsersArticles a "
                  + "WHERE a.articles_Id = :articleId"
    ),
    @NamedQuery(
            name  = "Users_Articles.ArticleWin",
            query = "SELECT a FROM UsersArticles a "
                  + "WHERE a.user_UserId = :uId "
    )
})
public class UsersArticles {
    
    @Id
    private Long user_UserId;
    
    @Id
    private Long articles_Id;
    
    private double enchere;

    public UsersArticles(){
        
    }

    public Long getUser_UserId() {
        return user_UserId;
    }

    public void setUser_UserId(Long user_UserId) {
        this.user_UserId = user_UserId;
    }

    public Long getArticles_Id() {
        return articles_Id;
    }

    public void setArticles_Id(Long article_Id) {
        this.articles_Id = article_Id;
    }

    public double getEnchere() {
        return enchere;
    }

    public void setEnchere(double enchere) {
        this.enchere = enchere;
    }    
    
}
