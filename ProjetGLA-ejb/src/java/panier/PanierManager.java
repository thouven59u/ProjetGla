/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panier;

import entities.Article;
import entities.User;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Benoît
 */
@Local
public interface PanierManager {
    public void addArticle(long id, long idArticle);
    public void deleteArticle(long id, long idArticle);
    public List<Article> getArticles(long id);
    public double getPrix(Long userId);
}
