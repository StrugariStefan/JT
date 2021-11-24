package ro.uaic.info.laborator6.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author stefa
 */
@Entity(name = "Exam")
@Table(name = "EXAMS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE", discriminatorType = DiscriminatorType.STRING)
public class Exam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "STARTINGTIME")
    private LocalDateTime startingTime;
    
    @Column(name = "DURATIONINMINUTES")
    private short durationInMinutes;
    
    public Exam() {
    }

    public Exam(String name, LocalDateTime startingTime, short durationInMinutes) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        
        if (startingTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Starting time must be a date in the future");
        }
        
        if (durationInMinutes <= 0) {
            throw new IllegalArgumentException("Duration in minutes must be represented as a strictly positive integer");
        }
        
        this.name = name;
        this.startingTime = startingTime;
        this.durationInMinutes = durationInMinutes;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public LocalDateTime getStartingTime() {
        return startingTime;
    }
    
    public short getDurationInMinutes() {
        return durationInMinutes;
    }
    
    public void setDurationInMinutes(short durationInMinutes) {
        if (durationInMinutes <= 0) {
            throw new IllegalArgumentException("Duration in minutes must be represented as a strictly positive integer");
        }
        
        this.durationInMinutes = durationInMinutes;
    }
    
    @Override
    public String toString() { 
        return id + ": " + name; 
    }
    
    @ManyToMany
    Set<Student> attendingStudents;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name = "EXAM_RESOURCE", 
        joinColumns = @JoinColumn(name = "EXAM_ID"), 
        inverseJoinColumns = @JoinColumn(name = "RESOURCE_ID"))
    Set<Resource> resourcesForTheExam;
    
    public boolean hasResourcesAssigned() {
        return !resourcesForTheExam.isEmpty();
    }
}
