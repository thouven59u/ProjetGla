
import authentication.AuthenticationManager;
import article.ArticleManager;
import entities.Article;
import entities.User;
import java.util.List;
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
    @EJB
    private ArticleManager articleManager;
    
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
    public String myArt() {
        return "mesArticle";
    }
    
    public List<Article> articelWin() {
        return articleManager.articleWin(this.cUsr.getUser().getUserId());  
    }
    
    public String getUser() { 
        return u.toString();
        
    }
    
    public String getLogin() {
        return u.getLogin();
    }
    
     
}
