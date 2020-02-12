/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article;

import entities.Article;
import entities.User;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import authentication.*;

/**
 *
 * @author Benoît
 */
@Stateless
public class ArticleManagerBean implements ArticleManager {

    @PersistenceContext(unitName = "truc-PU")
    private EntityManager em;
    
    @EJB
    private AuthenticationManager authenticationManager;
    
    
    @Override
    public void addArticle(User user, String name, String description, double price, String category, Date endAuction) {
        Article a = new Article();
        a.setUser(user);
        a.setName(name);
        a.setDescription(description);
        a.setPrice(price);
        a.setCategory(category);
        a.setAuctionEnd(endAuction);
        em.persist(a);
    }
    @Override
    public List<Article> allArticles(){
        Query q = em.createNamedQuery("Article.all");
        List<Article> list = (List<Article>) q.getResultList();
        return list;
    }

    @Override
    public boolean delArticle(long id) {
        Query q = em.createNamedQuery("Article.delById");
        q.setParameter("id", id);
        return q.executeUpdate() == 1 ? true : false;
    }
    
    @Override
    public Article getArticleById(long id){
        Query q = em.createNamedQuery("Article.find");
        q.setParameter("id", id);
        return (Article) q.getSingleResult();
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

}
