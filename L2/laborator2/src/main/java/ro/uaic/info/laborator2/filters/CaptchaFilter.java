package ro.uaic.info.laborator2.filters;

import java.io.IOException;
import java.util.Date;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.javatuples.Pair;
import ro.uaic.info.laborator2.captcha.CaptchaGenerator;

/**
 *
 * @author stefa
 */
@WebFilter(filterName = "CaptchaFilter", urlPatterns = {"/input.jsp"}, dispatcherTypes = {DispatcherType.REQUEST})
public class CaptchaFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        
        logRequest(httpServletRequest);
        setCaptcha(httpServletRequest);
        
        chain.doFilter(httpServletRequest, response);
    }
    
    private void logRequest(HttpServletRequest httpServletRequest) {
        String servletPath = httpServletRequest.getServletPath();
        String remoteAddr = httpServletRequest.getRemoteAddr();
        
        System.out.println(" ");
        System.out.println("Logging Date: " + new Date());
        System.out.println("Servlet path: " + servletPath );
        System.out.println("Call from : " + remoteAddr);
    }
    
    private void setCaptcha(HttpServletRequest httpServletRequest) {
        Pair<Integer, String> captcha = CaptchaGenerator.getRandomCaptcha();
        HttpSession session = httpServletRequest.getSession(true);
        session.setAttribute("captchaId", captcha.getValue0());
        httpServletRequest.setAttribute("captchaExpression", captcha.getValue1());
    }
}
