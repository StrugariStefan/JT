package ro.uaic.info.laborator4.presentation;

import java.time.LocalDateTime;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import ro.uaic.info.laborator4.domain.ExamsRepository;
import ro.uaic.info.laborator4.domain.Exam;

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
    
    public String Create() {
        Exam exam;
        
        try {
            exam = new Exam(this.name, this.startingTime, this.durationInMinutes);

            examsRepository.Create(exam);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return "index.xhtml";
    }
}
