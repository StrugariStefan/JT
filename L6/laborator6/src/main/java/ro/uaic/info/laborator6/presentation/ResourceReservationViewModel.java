package ro.uaic.info.laborator6.presentation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import ro.uaic.info.laborator6.business.ResourcesAssignmentService;
import ro.uaic.info.laborator6.business.ResourcesStatusService;
import ro.uaic.info.laborator6.domain.Exam;
import ro.uaic.info.laborator6.domain.Resource;

/**
 *
 * @author stefa
 */
@Named
@RequestScoped
public class ResourceReservationViewModel {
    
    private int selectedExam;
    
    private int[] selectedResources;
    
    @EJB
    private ResourcesStatusService resourcesStatusService;
    
    @EJB
    private ResourcesAssignmentService reservationService;
    
    public void setSelectedExam(int selectedExam) {
        this.selectedExam = selectedExam;
    }
    
    public int getSelectedExam() {
        return selectedExam;
    }
    
    public void setSelectedResources(int[] selectedResources) {
        this.selectedResources = selectedResources;
    }
    
    public int[] getSelectedResources() {
        return selectedResources;
    }
    
    private Exam[] existingExams;
    
    private Resource[] existingResources;
    
    public Exam[] getExistingExams() {
      existingExams = resourcesStatusService.getExamsForWhichResourcesWhereNotAssigned().toArray(new Exam[0]);

      return existingExams;
    }
    
    public Resource[] getExistingResources() {
      existingResources = resourcesStatusService.getAvailableResources().toArray(new Resource[0]);

      return existingResources;
    }
    
    public String AddResources() {
        Exam exam = Arrays
                .asList(existingExams)
                .stream()
                .filter((Exam e) -> selectedExam == e.getId())
                .findFirst()
                .get();
        
        List<Integer> selectedResourceIdList = Arrays.stream(selectedResources).boxed().collect(Collectors.toList());
        
        List<Resource> resources = 
            Arrays
                .asList(existingResources)
                .stream()
                .filter((Resource r) -> selectedResourceIdList.contains(r.getId()))
                .collect(Collectors.toList());
        
        reservationService.assingResourcesForExam(exam, resources);
        
        return "index.xhtml";
    }
}
