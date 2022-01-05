package ro.uaic.info.laborator9.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author stefa
 */
@Entity(name = "Group")
@Table(name = "GROUPS")
public class Group implements Serializable {
    @Id
    @Column(name = "GROUPID", nullable = false)
    private String groupid;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="group")
    private Set<User> users;
    
    public Group() {
    }
    
    public Group(@NotNull @NotEmpty  String role) {
        this.groupid = role;
    }
    
    public String getGroupid() {
        return groupid;
    }
}
