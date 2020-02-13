package entities;

import java.io.Serializable; 
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
    ),
    @NamedQuery(
            name  = "User.findById",
            query = "SELECT u from User u "
                  + "WHERE u.userId = :id" 
    )
})

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @OneToMany
    private Set<Article> articles;

    @Column(unique=true)
    private String login;

    private String password;
    
    private int cancelCount;

    public User (){
        
    }    

    public int getCancelCount() {
        return cancelCount;
    }

    public void setCancelCount(int cancelCount) {
        this.cancelCount = cancelCount;
    }

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

    public Long getUserId() {
        return userId;
    }
 
}