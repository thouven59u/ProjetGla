
import article.ArticleManager;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import authentication.*;
import entities.Article;
import entities.User;
import entities.UsersArticles;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
    private String search;
    private List<Article> articles;
    
    public ListeArticleBean(){
        this.price = 0.0;
        this.search ="tous";
        this.articles = new ArrayList<>();
    }
    
    @PostConstruct
    public void init( ) {
         this.articles =  this.articleBean.allArticles();
    }
    
    public void allArticles(){
        if(!this.search.equals("tous")) {
            this.articles = this.articleBean.getArticleByCat(this.search);
        }else{
            this.articles =  this.articleBean.allArticles();
        }
    }
    public List<Article> myArticles(){
        User u = this.cUsr.getUser();
        return this.articleBean.myArticles(u.getUserId());
    }
    
    public String delArticle(long id){        
        articleBean.delArticle(id);
        return "test";
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
    public String getSearch() {
        return this.search;
    }
    
    public void setSearch(String s ) {
        this.search = s;
    }
    
    public List<Article> getArticles(){
        return this.articles;
    }
    
    public void setArticles(List<Article> la){
        this.articles =la;
    }
    
    public String toStringDate(Date d) throws ParseException{ 
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyy hh:mm a");
        return formatter.format(d);
    }
}
