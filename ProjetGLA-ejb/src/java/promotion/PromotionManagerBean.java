/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package promotion;

import entities.Article;
import entities.Promotion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author quent
 */
@Stateless
public class PromotionManagerBean implements PromotionManager {
    
    public final static String LIVRAISON = "LIVRAISON";
    public final static String REDUCTION = "REDUCTION";
    
    @PersistenceContext(unitName = "truc-PU")
    private EntityManager em;

    @Override
    public void deleteAll(){
        Query q = em.createNamedQuery("Promotion.deleteAll");
        q.executeUpdate();
        
        System.out.println("Promotion manager : delete all...");
    }
    @Override
    public void createPromotions(){
       System.out.println("Promotion manager : create new...");
       
       List<Article> articles = em.createQuery("SELECT a FROM Article a order by function('RANDOM')").setMaxResults(5).getResultList();
        for (Article article : articles) {
            Promotion p = new Promotion();
            p.setType(this.randomType());
            p.setArticle(article);
            em.persist(p);
        }
        
    }
    
    private String randomType(){
        if(Math.random()>0.5)
            return LIVRAISON;
        return REDUCTION;
    }

    @Override
    public List<Promotion> getAll() {
        return em.createNamedQuery("Promotion.getAll").getResultList();
    }
    
    @Override
    public Promotion getByArticleId(long id){
        try{
            Query qq = em.createNamedQuery("Article.find");
            qq.setParameter("id", id);
            Article a = (Article)qq.getSingleResult();
            Query q = em.createNamedQuery("Promotion.getByArticleId");
            q.setParameter("id", a);
            return (Promotion)q.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }
}
