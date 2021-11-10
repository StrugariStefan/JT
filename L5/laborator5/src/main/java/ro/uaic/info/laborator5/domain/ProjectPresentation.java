package ro.uaic.info.laborator5.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author stefa
 */
@Entity
@DiscriminatorValue("PRESENTATION")
public class ProjectPresentation extends Exam {
    
    @Column(name = "RESOURCES")
    private String resources;
    
    public ProjectPresentation() {
    }

    public ProjectPresentation(String name, LocalDateTime startingTime, short durationInMinutes, String resources) {
        super(name, startingTime, durationInMinutes);
        
        if (resources == null || resources.isEmpty()) {
            throw new IllegalArgumentException("Resources must not be null or empty");
        }
        
        this.resources = resources;
    }
    
    public String getResources() {
        return resources;
    }
}
