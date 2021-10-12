<%-- 
    Document   : error
    Created on : Oct 12, 2021, 11:58:11 PM
    Author     : stefa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset='UTF-8'>
        <title>Error message</title>
        <link rel='stylesheet' href='styles.css'/>
    </head>
    <body>
        <header class='header error'>
            <h1>Error message</h1>
            <p>${errorMessage}</p>
        </header>
    </body>
</html>
