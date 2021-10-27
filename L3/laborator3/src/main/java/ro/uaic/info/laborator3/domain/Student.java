package ro.uaic.info.laborator3.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity(name = "Student")
@Table(name = "STUDENTS")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "NAME")
    private String name;
    
    public Student() {
    }
    
    public Student(String name, List<Exam> exams) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty.");
        }
        
        if (exams == null || exams.isEmpty()) {
            throw new IllegalArgumentException("The student must attend at least one exam.");
        }
        
        this.name = name;
        this.examsToAttend = new HashSet<Exam>();
        this.examsToAttend.addAll(exams);
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return id + ": " + name;
    }
    
    @ManyToMany
    @JoinTable(
        name = "EXAM_STUDENT", 
        joinColumns = @JoinColumn(name = "STUDENT_ID"), 
        inverseJoinColumns = @JoinColumn(name = "EXAM_ID"))
    Set<Exam> examsToAttend;
}
