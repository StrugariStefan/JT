<%@page import="ro.uaic.info.laborator2.dictionary.Word"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset='UTF-8'/>
        <title>Word List</title>
        <link rel='stylesheet' href='styles.css'/>
    </head>
    <body>
        <header class='header'>
            <h1>Word List</h1>
        </header>
        
        <%List<Word> words =  
                (List<Word>)request.getAttribute("words"); 
                for(Word w: words){%> 
        <section class='section section--gray center'>
            <h2><strong><%=w.getValue()%></strong></h2>
            <h3><em><%=w.getCategory()%></em></h3>
            
            <p><%=w.getDefinition()%></p>
        </section>
        <%}%>
    </body>
</html>
