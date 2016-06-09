<%-- 
    Document   : prodottoAggiunto
    Created on : 19-mag-2016, 16.03.05
    Author     : Robi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Prodotto Aggiunto</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="keywords" content="HTML, AMAZON, e-commerce, login">
        <meta name="description" content="Milestone1">
        <meta name="author" content="Roberto Spiga">
        <link href="M3/style.css" rel="stylesheet" type="text/css" media="screen" />
        <base href="http://localhost:8080/AmmM1/">
    </head>
    <body id="prodotto"> 
        <!-- Container -->
        <div class="container">
            <!-- Header -->
            <div class="header">
                Benvenuto ${venditore.username}
                <form method="post" action="login.html">
                    <button type="submit" name="LogOut">Esci</button>
                </form> 
            </div>
                
            <!-- Content -->
            <div class="content">
                <p>PRODOTTO AGGIUNTO</p>
                <div id="resoconto">
                    <p>Nome prodotto : ${prodotto.nome}</p>
                    <p>Url immagine prodotto : ${prodotto.imgurl}</p>
                    <p>Tipologia prodotto : ${prodotto.tipologia}</p>
                    <p>Prezzo prodotto : ${prodotto.prezzo}</p>
                    <p>Pezzi prodotto : ${prodotto.pezzi}</p>
                </div>
            </div>

            <!-- Sidebar -->
            <jsp:include page="sidebarV.jsp" />


        </div>    
    </body>
</html>
