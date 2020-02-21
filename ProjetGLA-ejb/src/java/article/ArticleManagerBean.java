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
import entities.UsersArticles;
import java.util.ArrayList;
import javax.ejb.EJBException;
import javax.persistence.NoResultException;
import javax.persistence.TemporalType;

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
    public void addArticle(User user, String name, String description, double price, String categorie, Date endAuction) {
        Article a = new Article();
        a.setUser(user);
        a.setName(name);
        a.setDescription(description);
        a.setPrice(price);
        a.setCategorie(categorie);
        a.setAuctionEnd(endAuction);
        em.persist(a);
    }
    @Override
    public List<Article> allArticles(){
        Date now = new Date();
        Query q = em.createNamedQuery("Article.all");
        q.setParameter("today",now,TemporalType.DATE);
        List<Article> list = (List<Article>) q.getResultList();
        return list;
    }
    
    @Override
    public List<Article> myArticles(long id){
        Query q = em.createNamedQuery("User.findById");
        q.setParameter("id", id);
        User u = (User) q.getSingleResult();
        Query q2 = em.createNamedQuery("Article.byUser");
        q2.setParameter("user",u);
        List<Article> list = (List<Article>) q2.getResultList();
        return list;
    }

    @Override
    public boolean delArticle(long id) {
        em.remove(em.createNamedQuery("Article.find").setParameter("id", id).getSingleResult());
        return true;
    }
    
    @Override
    public Article getArticleById(long id){
        Query q = em.createNamedQuery("Article.find");
        q.setParameter("id", id);
        return (Article) q.getSingleResult();
    }
    
    @Override
    public void modifyPrice(long idArticle, long idUser, double price){
        Query q = em.createNamedQuery("Users_Articles.find");
        q.setParameter("uId",idUser);
        q.setParameter("aId",idArticle);
        try{
            UsersArticles a = (UsersArticles) q.getSingleResult();
            a.setEnchere(price);
        }catch(NoResultException e){
            UsersArticles ua = new UsersArticles();
            ua.setUser_UserId(idUser);
            ua.setArticles_Id(idArticle);
            ua.setEnchere(price);
            em.persist(ua);
        }
    }
    
    @Override
    public UsersArticles findBestBet(long id){
        UsersArticles ua = null;
        Query q = em.createNamedQuery("Users_Articles.findArticle");
        q.setParameter("articleId", id);
        try{
            List<UsersArticles> lUA = q.getResultList();
            for(UsersArticles u : lUA){
                if(ua == null || u.getEnchere() > ua.getEnchere())
                    ua = u;
            }
        }catch(NoResultException e){
            
        }
        return ua;
    }

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }
    
    @Override
    public List<Article> articleWin(long idUser) {
        Query q = em.createNamedQuery("Users_Articles.ArticleWin");
        q.setParameter("uId",idUser);
        List<UsersArticles> list = (List<UsersArticles>) q.getResultList();
        List<Article> listA = new ArrayList<Article>();
        Date d= new Date();
        for (UsersArticles usersArticles : list) {
            Article a =getArticleById(usersArticles.getArticles_Id());
            if(a.getAuctionEnd().getTime() < d.getTime()){
                listA.add(a);
            }
        }
        return listA;
    }
    
    @Override
    public void cancelBet(long idArticle, long idUser, boolean estFini){
        Query q = em.createNamedQuery("Users_Articles.find");
        q.setParameter("aId", idArticle);
        q.setParameter("uId", idUser);
        try{
            UsersArticles ua = (UsersArticles) q.getSingleResult();
            em.remove(ua);
        }catch(NoResultException e){}
        
        if(estFini){
            Query q2 = em.createNamedQuery("User.findById");
            q2.setParameter("id", idUser);
            User u = (User)q2.getSingleResult();
            u.setCancelCount(u.getCancelCount()+1);
        }
    }
    
    	    /**
     *
     * @param cat
     * @return
     */
    @Override   
    public List<Article> getArticleByCat(String cat){
        Date now = new Date();
        Query q;
        q = em.createNamedQuery("Article.findByCat");
        q.setParameter("today",now,TemporalType.DATE);
        q.setParameter("cat", cat);
        return (List<Article>) q.getResultList();
    }

}
