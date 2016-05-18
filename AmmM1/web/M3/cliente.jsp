<%-- 
    Document   : cliente
    Created on : 12-mag-2016, 17.35.22
    Author     : Robi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<html>
    <head>
        <title>cliente</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="keywords" content="HTML, AMAZON, e-commerce, cliente">
        <meta name="description" content="Milestone1">
        <meta name="author" content="Roberto Spiga">
        <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
    </head>
    <body id="cliente">
        <c:if test="${venditore != null}">

            <!-- Container -->
            <div class="container">

                <!-- Header -->
                <div class="header">
                    Benvenuto ${cliente.username}
                    <h1>Prodotti</h1>
                </div>

                <!-- Content -->
                <div class="content">
                    <table id="tabella">
                        <tr>
                            <th>Nome</th>
                            <th>Foto</th>
                            <th>Pezzi</th>
                            <th>Prezzo</th>
                            <th>Aggiungi al Carrello</th>
                        </tr>


                        <c:forEach var="prodotto" items="${cliente.getProdottiAcquistabili}">

                            <c:if test="${prodotto.cod%2 != 0}">
                                <tr class="disp">
                            </c:if>

                            <c:if test="${prodotto.cod%2 == 0}">
                                <tr class="pari">        
                            </c:if>

                                    <td>${prodotto.nome}</td>
                                    <!-- Immagine -->
                                    <td><img src="${prodotto.imgurl}" title="${prodotto.nome}" alt="${prodotto.nome}"></td>
                                    <td>${prodotto.pezzi}</td>
                                    <td>${prodotto.prezzo}</td>
                                    <td><a href="cliente.jsp">${prodotto.nome}</a></td>
                                </tr>

                        </c:forEach>

                    </table>          
                </div>

                <!-- Sidebar -->
                <div class="sidebar">
                    <a href="descrizione.html">Descrizione</a>
                    <a href="login.html">Login</a>
                </div>

                <!-- Footer -->
                <!--
                <div class="footer">
                    Footer
                </div>
                -->

            </div>  
        </c:if>
        <c:if test="${cliente == null}">
        Errore nel caricamento della pagina, torna indietro. 
        </c:if>
    </body>
</html>




