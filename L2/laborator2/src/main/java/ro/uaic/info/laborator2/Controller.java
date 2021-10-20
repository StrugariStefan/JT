package ro.uaic.info.laborator2;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ro.uaic.info.laborator2.captcha.CaptchaGenerator;
import ro.uaic.info.laborator2.dictionary.Word;
import ro.uaic.info.laborator2.dictionary.WordsRepository;

/**
 *
 * @author stefa
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller"}, initParams = { @WebInitParam(name = "category", value = "Category3") })
public class Controller extends HttpServlet {

    @EJB
    private WordsRepository wordsRepository;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String value = request.getParameter("value");
        String definition = request.getParameter("definition");
        String category = request.getParameter("category");
        String captcha = request.getParameter("captcha");
        
        HttpSession session = request.getSession(false);
        int captchaId  = (int) session.getAttribute("captchaId");
        int expectedCaptchaResult = CaptchaGenerator.getResult(captchaId);
        
        if (expectedCaptchaResult != Integer.parseInt(captcha)) {
            request.setAttribute("errorMessage", "Invalid result for the mathematical expression");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            
            return;
        }
        
        if (category == null || category.isEmpty()) {
            category = (String) request.getServletContext().getAttribute("defaultCategory");
        }
        
        try {
            Word word = new Word(value, definition, category);
            wordsRepository.Create(word);
            List<Word> words = wordsRepository.Get();
            
            Cookie categoryCookie = new Cookie("category", word.getCategory());
            response.addCookie(categoryCookie);
            
            request.setAttribute("words" , words);
            request.getRequestDispatcher("result.jsp").forward(request, response);
            
        } catch(Exception e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
