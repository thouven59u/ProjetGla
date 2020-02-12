
import article.ArticleManager;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import entities.Article;

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
    private ArticleManager articleBean;

    public List<Article> allArticles(){
        return this.articleBean.allArticles();
    }
}
