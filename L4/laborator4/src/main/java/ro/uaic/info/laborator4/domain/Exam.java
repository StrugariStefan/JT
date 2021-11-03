package ro.uaic.info.laborator4.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author stefa
 */
@Entity(name = "Exam")
@Table(name = "EXAMS")
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
    
    @Override
    public String toString() { 
        return id + ": " + name; 
    }
    
    @ManyToMany
    Set<Student> attendingStudents;
}
