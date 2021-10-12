package ro.uaic.info.laborator1.filters;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.javatuples.Quartet;
import ro.uaic.info.laborator1.models.KeyValueInputModel;

/**
 *
 * @author stefa
 */
@WebFilter(filterName = "KeyValueFormValidationFilter", servletNames = {"KeyValueServlet"}, dispatcherTypes = {DispatcherType.REQUEST})
public class KeyValueFormValidationFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        Quartet<String, String, String, String> parameters = getRequestParameters(request);
        
        logRequestInformation((HttpServletRequest) request, parameters);
        
        Optional<String> maybeErrorMessage = buildErrorMessage(parameters);
        
        if (maybeErrorMessage.isPresent()) {
            request.setAttribute("errorMessage", maybeErrorMessage.get());
            request.getRequestDispatcher("error.jsp").forward(request, response);
            
            return;
        }
        
        KeyValueInputModel keyValueInputModel = mapInputModelFromRequest(parameters);
        
        request.setAttribute("keyValueInputModel", keyValueInputModel);
        chain.doFilter(request, response);
    }

    
    private Quartet<String, String, String, String> getRequestParameters(ServletRequest request) {
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
        
    private void logRequestInformation(HttpServletRequest request, Quartet<String, String, String, String> parameters) {
        System.out.println("IP address: " + request.getRemoteAddr());
        System.out.println("HTTP method: " + request.getMethod());
        System.out.println("User agent: " + request.getHeader("User-Agent"));
        System.out.println("Language(s): " + request.getHeader("Accept-Language"));
        
        System.out.println(
                "Request parameters:\n" +
                "\tKey: " + parameters.getValue0() + "\n" + 
                "\tValue: " + parameters.getValue1() + "\n" + 
                "\tMock: " + parameters.getValue2() + "\n" + 
                "\tSync: " + parameters.getValue3() + "\n");
    }
    
    private Optional<String> buildErrorMessage(Quartet<String, String, String, String> parameters) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        
        if (parameters.getValue0() == null || parameters.getValue0().isEmpty()) {
            errorMessageBuilder.append("The key shoult not be null or empty.\n");
        }
        
        try {
            int value = Integer.parseInt(parameters.getValue1());
            
            if (value <= 0) {
                errorMessageBuilder.append("The value should be a positive integer.\n");
            }
            
        } catch (NumberFormatException e) {
            errorMessageBuilder.append("The value should be a positive integer.\n");
        }
        
        String errorMessage = errorMessageBuilder.toString();
        
        return errorMessage.isEmpty() 
                ? Optional.empty() 
                : Optional.of(errorMessage);
    }
    
    private KeyValueInputModel mapInputModelFromRequest(Quartet<String, String, String, String> parameters ) {
        String key = parameters.getValue0();
        int value = Integer.parseInt(parameters.getValue1());
        boolean mock = "is-mock".equals(parameters.getValue2());
        boolean sync = "is-sync".equals(parameters.getValue3());
        
        return new KeyValueInputModel(key, value, mock, sync);
    }
}
