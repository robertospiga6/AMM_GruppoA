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
    </head>
    <body> 
        <c:if test="${formArticolo}">
        Effettuato  :    ${articolo.nome}  -  ${articolo.imgurl}  -  ${articolo.tipologia}  -  ${articolo.prezzo}  -  ${articolo.pezzi}
        </c:if>
        <c:if test="${formArticolo==false}">
        Errore, torna indietro.
        </c:if>
    </body>
</html>
