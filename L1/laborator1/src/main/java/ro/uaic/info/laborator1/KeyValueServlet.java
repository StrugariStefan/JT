package ro.uaic.info.laborator1;

import ro.uaic.info.laborator1.data.KeyValueRepository;
import ro.uaic.info.laborator1.models.KeyValueInputModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stefa
 */
@WebServlet(name = "KeyValueServlet", urlPatterns = {"/KeyValueServlet"})
public class KeyValueServlet extends HttpServlet {

    private final KeyValueRepository repository;
    
    public KeyValueServlet() throws IOException {
        repository = new KeyValueRepository();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        KeyValueInputModel keyValueInputModel = (KeyValueInputModel) request.getAttribute("keyValueInputModel");
        
        if (keyValueInputModel.isMock()) {
            request.getRequestDispatcher("mock.jsp").forward(request, response);
            
            return;
        }
        
        repository.updateRepository(keyValueInputModel);
        String repositoryContent = repository.getRepositoryContent();
        
        request.setAttribute("content", repositoryContent);
        request.getRequestDispatcher("repository.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
