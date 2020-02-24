
import article.ArticleManager;
import authentication.AuthenticationManager;
import entities.Article;
import entities.User;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Destination;
import javax.jms.JMSContext;
import panier.PanierManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Benoît
 */
@Named(value = "panierBean")
@RequestScoped
public class PanierBean {
    @EJB
    private AuthenticationManager cUsr;
    @EJB
    private PanierManager panierManager;    
    
    @Inject
    JMSContext context;
    
    @Resource(lookup = "jms/OrderQueue")
    Destination livraisonQueue;
    
    @Resource(lookup = "jms/FacturationQueue")
    Destination facturationQueue;
    
    private User u;
    private String nom, prenom, adresse, iban;
    
    @PostConstruct
    public void init( ) {
        u = this.cUsr.getUser();
        this.nom = u.getNom();
        this.prenom = u.getPrenom();
        this.adresse = u.getAdresse();
        this.iban = u.getIban();
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
    
    public List<Article> getArticles(){
        return panierManager.getArticles(this.cUsr.getUser().getUserId());
    }
    
    public String addArticle(long idArticle){
        panierManager.addArticle(this.cUsr.getUser().getUserId(), idArticle);
        return "test";
    }
    
    public String deleteArticle(long idArticle){
        panierManager.deleteArticle(this.cUsr.getUser().getUserId(), idArticle);
        return "test";
    }
    
    public double getPrix(){
        return panierManager.getPrix(this.cUsr.getUser().getUserId());
    }
    
    public double getPrixPromotions(){
        return panierManager.getPrixPromotions(this.cUsr.getUser().getUserId());
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }    
    
    public void validerPanier(){
        sendMessageLivraison("Livraison à : "+u.getAdresse());
        sendMessageFacturation("Facturation à : "+u.getIban());
    }
    
    public void sendMessageLivraison(String message) {
        context.createProducer().send(livraisonQueue, message);
    }
    
    public void sendMessageFacturation(String message) {
        context.createProducer().send(facturationQueue, message);
    }
    
    
}
