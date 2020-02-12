/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package article;

import entities.Article;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Benoît
 */
@Local
public interface ArticleManager {
    public void addArticle(String name, String description, double price, String category, Date endAuction);
    public List<Article> allArticles();
}
