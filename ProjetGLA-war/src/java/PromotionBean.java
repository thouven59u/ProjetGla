
import article.ArticleManager;
import entities.Article;
import entities.Promotion;
import entities.UsersArticles;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import promotion.PromotionManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Benoît
 */
@Named(value = "promotionBean")
@RequestScoped
public class PromotionBean {
    
    @EJB
    private PromotionManager promotionBean;
    @EJB
    private ArticleManager articleBean;
    
    private String type;
    
    public PromotionBean(){
        type = "AUCUNE";
    }
    
    public String getPromotionLivraison(long id){
        Promotion p = promotionBean.getByArticleId(id);
        if(p != null){
            if(p.getType().equals("LIVRAISON"))
                return "0.0";
        }
        return "5.0";
    }
    
    public String getPromotionReduction(long id){
        Promotion p = promotionBean.getByArticleId(id);
        UsersArticles a = articleBean.findBestBet(id);
        double res = 0; 
        if(p != null && a != null){
            if(p.getType().equals("REDUCTION")){
                res = Math.round( (a.getEnchere()*0.10)*100.0 ) / 100.0; //Permet de récupérer 10% de la valeur de l'article et de l'arrondir 2 chiffres après la virgule
                return Double.toString(res);
            }
        }
        return "0.0";
    }
    
    public String getPrixFinal(long id){
        double enchere = articleBean.findBestBet(id).getEnchere();
        double liv = Double.parseDouble(this.getPromotionLivraison(id));
        double red = Double.parseDouble(this.getPromotionReduction(id));
        double res = enchere + liv - red;
        return Double.toString(res);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}
