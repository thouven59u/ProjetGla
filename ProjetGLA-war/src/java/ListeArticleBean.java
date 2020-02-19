
import article.ArticleManager;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import authentication.*;
import entities.Article;
import entities.User;
import entities.UsersArticles;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

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
    private AuthenticationManager cUsr;
    @EJB
    private ArticleManager articleBean;
    private double price;
    
    public ListeArticleBean(){
        this.price = 0.0;
    }
    
    public List<Article> allArticles(){
        return this.articleBean.allArticles();
    }
    
    public List<Article> myArticles(){
        User u = this.cUsr.getUser();
        return this.articleBean.myArticles(u.getUserId());
    }
    
    public String delArticle(long id){
        
        articleBean.delArticle(id);
        return "listeArticle";
    }
    
    public String encherir(long id){
        System.out.println("ListeArticleBean.encherir() "+ this.price);
        this.articleBean.modifyPrice(id, articleBean.getAuthenticationManager().getUser().getUserId(), this.price);
        return "listeArticle";
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    public boolean verifCreateur (long idArticle){
        if (articleBean.getAuthenticationManager().getUser() != null){
            //System.out.println(articleBean.getAuthenticationManager().getUser());
            //System.out.println(idArticle);
            //System.out.println(articleBean.getArticleById(idArticle).getUser());
            return articleBean.getAuthenticationManager().getUser().getUserId().equals(articleBean.getArticleById(idArticle).getUser().getUserId());
        } else {
            return false;
        }
    }
    
    public boolean verifDate (Date dateArticle){
        if (articleBean.getAuthenticationManager().getUser() != null){
            System.out.print(dateArticle);
            Date d= new Date();  
            return d.getTime() <= dateArticle.getTime() ;
        } else {
            return false;
        }
    }
    
    public boolean verifierCancelCount(){
        return (this.cUsr.getUser().getCancelCount() < 5);
    }
    
    public boolean verifier(Date dateArticle){
        return (verifDate(dateArticle) && verifierCancelCount());
    }
    
    public void check(FacesContext context, UIComponent comp, Object value) throws ValidatorException{
        Article a = (Article) articleBean.getArticleById((long)comp.getAttributes().get("idRow"));
        //System.out.println(a.getPrice()+" \\ "+value);
        if(a.getPrice() >= (double)value){
            throw new ValidatorException(new FacesMessage("L'enchère doit être supérieur à l'enchère minimum actuelle"));
        }
    }
    
    public String findBestBet(long id){
        UsersArticles ua = articleBean.findBestBet(id);
        if(ua != null){
            String login = cUsr.findById(ua.getUser_UserId()).getLogin();
            return ua.getEnchere()+"€ par "+login;
        }else{
            return "Aucune enchère placée";
        }
    }
    
    public String annuler(long id, boolean estFini){
        articleBean.cancelBet(id, this.cUsr.getUser().getUserId(), estFini);
        if(estFini){
            this.cUsr.getUser().setCancelCount(this.cUsr.getUser().getCancelCount()+1);
            return "test";
        }
        return "listeArticle";
    }
}
