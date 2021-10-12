package ro.uaic.info.laborator1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.javatuples.Quartet;

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
        
        logRequestInformation(request);
        KeyValueInputModel keyValueInputModel = mapInputModelFromRequest(request);
        
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
    
    private void logRequestInformation(HttpServletRequest request) {
        System.out.println("IP address: " + request.getRemoteAddr());
        System.out.println("HTTP method: " + request.getMethod());
        System.out.println("User agent: " + request.getHeader("User-Agent"));
        System.out.println("Language(s): " + request.getHeader("Accept-Language"));
        
        Quartet<String, String, String, String> parameters = getRequestParameters(request); 
        
        System.out.println(
                "Request parameters:\n" +
                "\tKey: " + parameters.getValue0() + "\n" + 
                "\tValue: " + parameters.getValue1() + "\n" + 
                "\tMock: " + parameters.getValue2() + "\n" + 
                "\tSync: " + parameters.getValue3() + "\n");
    }
    
    private KeyValueInputModel mapInputModelFromRequest(HttpServletRequest request) {
        Quartet<String, String, String, String> parameters = getRequestParameters(request); 
        
        String key = parameters.getValue0();
        int value = Integer.parseInt(parameters.getValue1());
        boolean mock = "is-mock".equals(parameters.getValue2());
        boolean sync = "is-sync".equals(parameters.getValue3());
        
        return new KeyValueInputModel(key, value, mock, sync);
    }
    
    private Quartet<String, String, String, String> getRequestParameters(HttpServletRequest request) {
        String keyParameter = request.getParameter("key");
        String valueParameter = request.getParameter("value");
        String mockParameter = request.getParameter("mock");
        String syncParameter = request.getParameter("sync");
        
        return new Quartet(
                keyParameter,
                valueParameter,
                mockParameter,
                syncParameter);
    }
}
