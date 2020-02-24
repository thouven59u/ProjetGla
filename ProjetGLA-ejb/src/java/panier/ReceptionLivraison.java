/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panier;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Benoît
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/RecepLivraison")
})
public class ReceptionLivraison implements MessageListener{
    
    @Resource
    private MessageDrivenContext context;
    
    String receptionLivraison;

    @Override
    public void onMessage(Message message) {
        try {
           String res = message.getBody(String.class);
           receptionLivraison = res;
           System.out.println("LIVRAISON : "+res);
        } catch (JMSException jmse) {
            jmse.printStackTrace();
            context.setRollbackOnly();
        }
    }
    
}
