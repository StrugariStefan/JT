package ro.uaic.info.laborator7.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author stefa
 */
@Entity(name = "User")
@Table(name = "USERS")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "USERNAME")
    private String username;
    
    @Column(name = "PASSWORD")
    private String password;
    
    @Column(name = "USERROLE")
    private String userRole;
    
    public User() {
    }
    
    public User(
        @NotNull @NotEmpty String username,
        @NotNull @NotEmpty String password,
        @NotNull @NotEmpty String userRole) {
        
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }
    
    public int getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getRole() {
        return userRole;
    }
}
