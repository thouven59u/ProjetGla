
import article.ArticleManager;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import authentication.*;
import entities.Article;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
@Named(value = "listeArticleBean")
@RequestScoped
public class ListeArticleBean {
    
    @EJB
    private ArticleManager articleBean;
    private double price;
    
    public List<Article> allArticles(){
        return this.articleBean.allArticles();
    }
    
    public String delArticle(long id){
        
        articleBean.delArticle(id);
        return "listeArticle";
    }
    public void Encherir(long id){
        System.out.println("ListeArticleBean.Encherir()"+ price);
        this.price = 0;
    }
    
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    public boolean verifCreateur (long idArticle){
        if (articleBean.getAuthenticationManager().getUser() != null){
            System.out.println(articleBean.getAuthenticationManager().getUser());
            System.out.println(idArticle);
            System.out.println(articleBean.getArticleById(idArticle).getUser());
            return articleBean.getAuthenticationManager().getUser().getUserId() == articleBean.getArticleById(idArticle).getUser().getUserId();
        } else {
            return false;
        }
    }
}
