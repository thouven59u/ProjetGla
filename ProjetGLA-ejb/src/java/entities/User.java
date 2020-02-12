package entities;

import java.io.Serializable; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author quent
 */
@Entity
@Table(name= "USERS")
@NamedQueries ({
    @NamedQuery(
            name  = "User.findByLogin",
            query = "SELECT u from User u "
                  + "WHERE u.login = :login" 
    )
})
public class User implements Serializable{
    
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long userId;
 
 private String login;
 
 private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", login=" + login + ", password=" + password + '}';
    }
 
    
 
}