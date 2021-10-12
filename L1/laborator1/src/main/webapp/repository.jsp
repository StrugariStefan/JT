<%-- 
    Document   : repository
    Created on : Oct 5, 2021, 10:39:10 PM
    Author     : stefa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset='UTF-8'>
        <title>Repository Content</title>
        <link rel='stylesheet' href='styles.css'/>
    </head>
    <body>
        <header class='header'>
            <h1>Repository Content</h1>
        </header>
        <div class='content-wrapper'>
            <pre class='highlight'>
                <code>
${content}
                </code>
            </pre>
        </div>
    </body>
</html>
