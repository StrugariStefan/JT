package ro.uaic.info.laborator5;

import static org.assertj.core.api.Assertions.*;
import java.time.LocalDateTime;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import junit.framework.TestCase;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ro.uaic.info.laborator5.data.ExamsRepositoryImpl;
import ro.uaic.info.laborator5.domain.Exam;

public class ExamsRepositoryTests extends TestCase  {
    private Context  ctx;
    private EJBContainer ejbContainer;
    
    @BeforeClass
    @Override
    public  void setUp() {
        ejbContainer = EJBContainer.createEJBContainer();
        System.out.println("Opening the container" );
        ctx = ejbContainer.getContext();
    }

    @AfterClass
    @Override
    public  void tearDown() {
        ejbContainer.close();
        System.out.println("Closing the container" );
    }
    
    @Test
    public void testCreate() throws NamingException {
        Exam exam = new Exam("Java Technologies", LocalDateTime.now(), (short)30);
        
        ExamsRepositoryImpl repository = (ExamsRepositoryImpl) ctx.lookup("java:global/classes/ExamsRepositoryImpl");
        
        repository.create(exam);
        
        Exam databaseExam = repository.getById(exam.getId());
        assertThat(databaseExam.getDurationInMinutes()).isEqualTo(exam.getDurationInMinutes());
    }
    
    @Test
    public void testUpdate() throws NamingException {
        Exam exam = new Exam("Java Technologies", LocalDateTime.now(), (short)30);
        
        ExamsRepositoryImpl repository = (ExamsRepositoryImpl) ctx.lookup("java:global/classes/ExamsRepositoryImpl");
        repository.create(exam);
        
        exam.setDurationInMinutes((short) 60);
        
        repository.update(exam);
        
        Exam databaseExam = repository.getById(exam.getId());
        assertThat(databaseExam.getDurationInMinutes()).isEqualTo(exam.getDurationInMinutes());
    }
    
    @Test
    public void testGetById() throws NamingException {
        Exam exam = new Exam("Java Technologies", LocalDateTime.now(), (short)30);
        
        ExamsRepositoryImpl repository = (ExamsRepositoryImpl) ctx.lookup("java:global/classes/ExamsRepositoryImpl");
        
        repository.create(exam);
        
        Exam databaseExam = repository.getById(exam.getId());
        assertThat(databaseExam.getDurationInMinutes()).isEqualTo(exam.getDurationInMinutes());
    }
    
    @Test
    public void testDelete() throws NamingException {
        Exam exam = new Exam("Java Technologies", LocalDateTime.now(), (short)30);
        
        ExamsRepositoryImpl repository = (ExamsRepositoryImpl) ctx.lookup("java:global/classes/ExamsRepositoryImpl");
        
        repository.create(exam);
        repository.delete(exam);
        
        Exam databaseExam = repository.getById(exam.getId());
        assertThat(databaseExam).isNull();
    }
}
