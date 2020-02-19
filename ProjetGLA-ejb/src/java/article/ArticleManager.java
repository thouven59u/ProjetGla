/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article;

import authentication.AuthenticationManager;
import entities.Article;
import entities.User;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Benoît
 */
@Local
public interface ArticleManager {
    public void addArticle(User user, String name, String description, double price, String category, Date endAuction);
    public List<Article> allArticles();
    public boolean delArticle(long id);
    public Article getArticleById(long id);
    public void modifyPrice(long idArticle, long idUser, double price);
    public AuthenticationManager getAuthenticationManager();
}
