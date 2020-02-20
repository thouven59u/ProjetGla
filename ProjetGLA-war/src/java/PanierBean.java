
import article.ArticleManager;
import authentication.AuthenticationManager;
import entities.Article;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import panier.PanierManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Benoît
 */
@Named(value = "panierBean")
@RequestScoped
public class PanierBean {
    @EJB
    private AuthenticationManager cUsr;
    @EJB
    private PanierManager panierManager;
    
    public List<Article> getArticles(){
        return panierManager.getArticles(this.cUsr.getUser().getUserId());
    }
    
    public String addArticle(long idArticle){
        panierManager.addArticle(this.cUsr.getUser().getUserId(), idArticle);
        return "test";
    }
    
    public String deleteArticle(long idArticle){
        panierManager.deleteArticle(this.cUsr.getUser().getUserId(), idArticle);
        return "test";
    }
    
    public double getPrix(){
        return panierManager.getPrix(this.cUsr.getUser().getUserId());
    }
}
