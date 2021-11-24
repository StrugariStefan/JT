package ro.uaic.info.laborator6.domain;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author stefa
 */
@Entity(name = "Resource")
@Table(name = "RESOURCES")
public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "CAPACITY")
    private int capacity;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name = "EXAM_RESOURCE", 
        joinColumns = @JoinColumn(name = "RESOURCE_ID"), 
        inverseJoinColumns = @JoinColumn(name = "EXAM_ID"))
    Set<Exam> examsThatUseTheResource;
    
    public Resource() {
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public void addExam(Exam exam) {
        examsThatUseTheResource.add(exam);
        capacity--;
    }
    
    public boolean isAvailable() {
        return capacity > 0;
    }
}
