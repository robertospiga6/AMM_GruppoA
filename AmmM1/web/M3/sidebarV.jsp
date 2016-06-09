<%-- 
    Document   : sidebarV
    Created on : 9-giu-2016, 10.33.19
    Author     : Robi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body id="sidebarV">
        <div class="sidebar">
            <a href="descrizione.html">Descrizione</a>
            <a href="login.html">Venditore</a>
            Saldo : ${venditore.saldo}
        </div>
    </body>
</html>
