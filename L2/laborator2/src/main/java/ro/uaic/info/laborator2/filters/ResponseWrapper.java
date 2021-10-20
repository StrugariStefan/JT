package ro.uaic.info.laborator2.filters;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author stefa
 */
public class ResponseWrapper extends HttpServletResponseWrapper {
    
    private final StringWriter output;
    
    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new StringWriter();
    }
    
    @Override
    public PrintWriter getWriter() {
        return new PrintWriter(output);
    }
    
    @Override
    public String toString() {
        return output.toString();
    }
}
