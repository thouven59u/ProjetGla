/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panier;

import article.ArticleManager;
import entities.Article;
import entities.Panier;
import entities.Promotion;
import entities.UsersArticles;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import promotion.PromotionManager;

/**
 *
 * @author Benoît
 */
@Stateless
public class PanierManagerBean implements PanierManager {

    @PersistenceContext(unitName = "truc-PU")
    private EntityManager em;
    
    @EJB
    private PromotionManager promotionBean;
    @EJB
    private ArticleManager articleBean;
    
    String recepFacture, recepLivraison;
    
    @Override
    public void addArticle(long id, long idArticle) {
        Query q = em.createNamedQuery("Panier.find");
        q.setParameter("id",id);
        try{
            Panier p = (Panier) q.getSingleResult();
            if(!p.getIdArticles().contains(idArticle))
                p.addArticle(idArticle);
        }catch(NoResultException e){
            Panier p = new Panier();
            p.setUser_UserId(id);
            p.addArticle(idArticle);
            em.persist(p);
        }
    }

    @Override
    public void deleteArticle(long id, long idArticle) {
        Query q = em.createNamedQuery("Panier.find");
        q.setParameter("id", id);
        Panier p = (Panier)q.getSingleResult();
        p.getIdArticles().remove(idArticle);
    }

    @Override
    public List<Article> getArticles(long id) {
        List<Article> la = new ArrayList<>();
        Query q = em.createNamedQuery("Panier.find");
        q.setParameter("id", id);
        try{
            List<Long> ll = ((Panier)q.getSingleResult()).getIdArticles();
            for(Long l : ll){
                Query q2 = em.createNamedQuery("Article.find");
                q2.setParameter("id", l);
                la.add((Article)q2.getSingleResult());
            }            
        }catch(NoResultException e){
            
        }
        return la;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public double getPrix(long userId) {
        double total = 0;
        try{
            Query q = em.createNamedQuery("Panier.find");
            q.setParameter("id", userId);
            Panier p = (Panier)q.getSingleResult();

            for(Long l : p.getIdArticles()){
                Query q2 = em.createNamedQuery("Users_Articles.find");
                q2.setParameter("aId", l);
                q2.setParameter("uId", userId);
                UsersArticles ua = (UsersArticles)q2.getSingleResult();
                total += ua.getEnchere();
            }
        }catch(NoResultException e){
            
        }
        return total;
    }
    
    @Override
    public double getPrixPromotions(long id){
        double total = 0;
        try{
            Query q = em.createNamedQuery("Panier.find");
            q.setParameter("id", id);
            Panier p = (Panier)q.getSingleResult();

            for(Long l : p.getIdArticles()){
                Query q2 = em.createNamedQuery("Users_Articles.find");
                q2.setParameter("aId", l);
                q2.setParameter("uId", id);
                UsersArticles ua = (UsersArticles)q2.getSingleResult();
                total += (ua.getEnchere() + this.getPrixPromotionLivraison(l) - this.getPromotionReduction(l));
            }
        }catch(NoResultException e){
                
        }
        return total;
    }
    
    public double getPrixPromotionLivraison(long id){
        Promotion p = promotionBean.getByArticleId(id);
        if(p != null){
            if(p.getType().equals("LIVRAISON"))
                return 0.0;
        }
        return 5.0;
    }
    
    public double getPromotionReduction(long id){
        Promotion p = promotionBean.getByArticleId(id);
        UsersArticles a = articleBean.findBestBet(id);
        double res = 0; 
        if(p != null && a != null){
            if(p.getType().equals("REDUCTION")){
                res = Math.round( (a.getEnchere()*0.10)*100.0 ) / 100.0; //Permet de récupérer 10% de la valeur de l'article et de l'arrondir 2 chiffres après la virgule
                return res;
            }
        }
        return 0.0;
    }

    @Override
    public String getRecepFacture() {
        return recepFacture;
    }

    @Override
    public void setRecepFacture(String s) {
        this.recepFacture = s;
    }

    @Override
    public String getRecepLivraison() {
        return recepLivraison;
    }

    @Override
    public void setRecepLivraison(String s) {
        this.recepLivraison = s;
    }
    
    
}
