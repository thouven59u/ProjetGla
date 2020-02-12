/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article;

import entities.Article;
import entities.User;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Benoît
 */
@Stateless
public class ArticleManagerBean implements ArticleManager {

    @PersistenceContext(unitName = "truc-PU")
    private EntityManager em;
    
    
    @Override
    public void addArticle(String name, String description, double price, String category, Date endAuction) {
        Article a = new Article();
        a.setName(name);
        a.setDescription(description);
        a.setPrice(price);
        a.setCategory(category);
        a.setAuctionEnd(endAuction);
        em.persist(a);
    }

}
