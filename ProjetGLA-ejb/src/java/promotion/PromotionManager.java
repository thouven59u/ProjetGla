/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package promotion;

import entities.Promotion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author quent
 */
@Local
public interface PromotionManager {
    public void deleteAll();
    public void createPromotions();
    public List<Promotion> getAll();
}
