package ro.uaic.info.laborator6.presentation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import ro.uaic.info.laborator6.domain.Exam;
import ro.uaic.info.laborator6.domain.ExamsRepository;
import ro.uaic.info.laborator6.domain.Student;
import ro.uaic.info.laborator6.domain.StudentsRepository;

/**
 *
 * @author stefa
 */
@Named
@RequestScoped
public class StudentViewModel {
    @NotNull(message="Name is required")
    private String name;
    
    private int[] selectedExams = {};
    
    @EJB
    private StudentsRepository studentsRepository;
    
    @EJB
    private ExamsRepository examsRepository;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setSelectedExams(int[] selectedExams) {
        this.selectedExams = selectedExams;
    }
    
    public int[] getSelectedExams() {
        return selectedExams;
    }
    
    private Exam[] existingExams;
  
    public Exam[] getExistingExams() {
      existingExams = examsRepository.get().toArray(new Exam[0]);

      return existingExams;
    }
    
    public String Create() {
        Student student;
        List<Integer> selectedExamIdList = Arrays.stream(selectedExams).boxed().collect(Collectors.toList());
        
        List<Exam> exams = 
            Arrays
                .asList(existingExams)
                .stream()
                .filter((Exam e) -> selectedExamIdList.contains(e.getId()))
                .collect(Collectors.toList());
        
        try {
            student = new Student(this.name, exams);
            studentsRepository.create(student);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return "index.xhtml";
    }
}
