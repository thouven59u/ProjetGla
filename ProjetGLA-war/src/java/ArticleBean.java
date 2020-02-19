
import article.ArticleManager;
import authentication.RegistrationManager;
import entities.Article;
import entities.User;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import authentication.*;
import entities.UsersArticles;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Benoît
 */
@Named(value = "articleBean")
@RequestScoped
public class ArticleBean {
    
    @EJB
    private ArticleManager articleBean;
    
    @EJB
    private AuthenticationManager authenticationManager;
    
    private String name, description, category;
    private Date auctionEnd;
    private double price;

    public ArticleBean() {
        this.name = "";
        this.description = "";
        this.category = "";
        this.price = 0.0;
        this.auctionEnd = new Date();
    }

    public ArticleManager getArticleBean() {
        return articleBean;
    }

    public void setArticleBean(ArticleManager articleBean) {
        this.articleBean = articleBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getAuctionEnd() {
        return auctionEnd;
    }

    public void setAuctionEnd(Date auctionEnd) {
        this.auctionEnd = auctionEnd;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String createArticle() {
        if (authenticationManager.getUser() != null) {
            this.articleBean.addArticle(authenticationManager.getUser(), name, description, price, category, auctionEnd);
            return "test";       
        } else {
            return "index";
        }
    }
    
    public User getCurrentUser(){
        return authenticationManager.getUser();
    }
}
