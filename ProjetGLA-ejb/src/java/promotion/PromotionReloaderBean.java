/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package promotion;

 
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Startup;

/**
 *
 * @author quent
 */
@Singleton
@LocalBean
@Startup
public class PromotionReloaderBean {
    
    @EJB
    PromotionManager pm;

    //@Schedule(hour = "*", minute = "*", second = "*/20") // Reload toutes les 20 sec
    @Schedule(hour = "*", minute = "*/1", second = "0") // Reload toutes les minutes
    //@Schedule() // Reload tous les jours à minuit 
    private void reload() {
        System.out.println("Promotion Reloader : reload promotions...");
        pm.deleteAll();
        pm.createPromotions();
        System.out.println("Promotion Reloader : promotions reloaded");
    }
}
