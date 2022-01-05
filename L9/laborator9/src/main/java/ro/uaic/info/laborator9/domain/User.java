package ro.uaic.info.laborator9.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @Column(name = "USERID", nullable = false)
    private String userid;
    
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUPID")
    private Group group;
    
    public User() {
    }
    
    public User(
        @NotNull @NotEmpty String userid,
        @NotNull @NotEmpty String password,
        @NotNull Group group) {
        
        this.userid = userid;
        this.password = password;
        this.group = group;
    }
    
    public String getUserid() {
        return userid;
    }
    
    public String getPassword() {
        return password;
    }
    
    public Group getGroup() {
        return group;
    }
}
