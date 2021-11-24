package ro.uaic.info.laborator6.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author stefa
 */
@Entity
@DiscriminatorValue("TEST")
public class WrittenTest extends Exam {
    
    @Column(name = "BIBLIOGRAPHY")
    private String bibliography;
    
    public WrittenTest() {
    }

    public WrittenTest(String name, LocalDateTime startingTime, short durationInMinutes, String bibliography) {
        super(name, startingTime, durationInMinutes);
        
        if (bibliography == null || bibliography.isEmpty()) {
            throw new IllegalArgumentException("Bibliography must not be null or empty");
        }
        
        this.bibliography = bibliography;
    }
    
    public String getBibliography() {
        return bibliography;
    }
}
