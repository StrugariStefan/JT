<%@page import="ro.uaic.info.laborator2.dictionary.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset='UTF-8'/>
        <title>Word Form</title>
        <link rel='stylesheet' href='styles.css'/>
    </head>
    <body>
        <header class='header'>
            <h1>Word Form</h1>
        </header>
                   
        <form class='word-form' method="post" action="Controller">
            <div class='form-row'>
                <label for='value'>Value</label>
                <input id='value' name='value' type='text'/>
            </div>

            <div class='form-row'>
                <label for='definition'>Definition</label>
                <textarea id='definition' name='definition'></textarea>
            </div>

            <div class='form-row'>
                <label for='category'>Category</label>
                <select id='category' name='category'>
                    <%
                        Cookie cookie = null;
                        Cookie[] cookies = null;
                        String option = "";

                        cookies = request.getCookies();

                        if(cookies != null){
                            for (int i = 0; i < cookies.length; i++) {
                                cookie = cookies[i];
                                if (cookie.getName().equals("category")) {
                                    option = cookie.getValue();
                                }
                             }
                        }
                        
                        out.print("<option selected></option>");
                        for(String category: Category.SupportedCategories){
                            if(option.equals(category)){
                                out.print("<option selected>" + category + "</option>");
                            }
                            else{
                                out.print("<option>" + category + "</option>");
                            }
                        }
                    %>
                </select>
            </div>
                
            <div class='form-row'>
                <label for='captcha'>CAPTCHA</label>
                <input id='captcha' name='captcha' type='text'/>
                <div class='instructions'>${captchaExpression}</div>
            </div>

            <div class='form-row'>
                <input type='submit' value='Submit'/>
            </div>
        </form>
    </body>
</html>
