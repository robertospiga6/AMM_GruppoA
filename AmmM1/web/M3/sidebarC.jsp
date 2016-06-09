<%-- 
    Document   : sidebarC
    Created on : 9-giu-2016, 10.33.40
    Author     : Robi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <a href="descrizione.html">Descrizione</a>
            <a href="login.html">Cliente</a>
            <form class="ricaricaC" method="post" action="cliente.html?ricaricaC=true&id=${cliente.id}">
                <p>
                    <label>Ricarica conto</label>
                    <select name="Ricarica" >
                        <option value="10.0" selected="selected">10</option>
                        <option value="20.0">20</option>
                        <option value="50.0">50</option>
                        <option value="100.0">100</option>
                        <option value="200.0">200</option>
                    </select>
                </p>
                <button type="submit" name="RicaricaC">Conferma</button>
            </form>
    </body>
</html>
