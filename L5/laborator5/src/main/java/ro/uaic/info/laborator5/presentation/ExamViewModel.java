package ro.uaic.info.laborator5.presentation;

import java.time.LocalDateTime;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import ro.uaic.info.laborator5.domain.ExamsRepository;
import ro.uaic.info.laborator5.domain.Exam;
import ro.uaic.info.laborator5.domain.ProjectPresentation;
import ro.uaic.info.laborator5.domain.WrittenTest;

/**
 *
 * @author stefa
 */
@Named
@RequestScoped
public class ExamViewModel {
    @NotNull
    private String name;
    
    @Future
    private LocalDateTime startingTime;
    
    @Digits(integer = 3, fraction = 0)
    private short durationInMinutes;
        
    @NotNull
    private String type;
    
    private String bibliography;
    
    private String resources;

    @EJB
    private ExamsRepository examsRepository;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public LocalDateTime getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(LocalDateTime startingTime) {
        this.startingTime = startingTime;
    }

    public short getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(short durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getBibliography() {
        return bibliography;
    }
    
    public void setBibliography(String bibliography) {
        this.bibliography = bibliography;
    }
    
    public String getResources() {
        return resources;
    }
    
    public void setResources(String resources) {
        this.resources = resources;
    }
    
    public String Create() {
        Exam exam;
        
        try {
            if (this.type.equalsIgnoreCase("TEST")) {
                exam = new WrittenTest(this.name, this.startingTime, this.durationInMinutes, this.bibliography);
            } else {
                exam = new ProjectPresentation(this.name, this.startingTime, this.durationInMinutes, this.resources);
            }
            
            examsRepository.create(exam);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return "index.xhtml";
    }
}
