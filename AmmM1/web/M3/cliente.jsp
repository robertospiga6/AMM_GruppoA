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
        <link href="M3/style.css" rel="stylesheet" type="text/css" media="screen" />
    </head>
    <body id="cliente">
        <c:choose>
            <c:when test="${cliente!=null}">
                <c:choose>
                        <c:when test="${saldoInsuff}">
                            <div>
                                <h2>Errore credito insufficiente</h2>
                            </div>
                        </c:when>
                        <c:when test="${conferma}">
                            <div>
                                <h2>Acquisto confermato</h2>
                            </div>
                        </c:when>
                        <c:when test="${riepilogo}">
                            <div>
                                <h2>Riepilogo dati prodotto</h2>                               
                                <p>
                                    <img title="${prodotto.getNome()}" src="M3/img/${prodotto.getImgurl()}" alt="${prodotto.getNome()}" width="96" height="96">
                                </p>
                                <p>
                                    <strong>Nome:</strong> ${prodotto.getNome()}
                                </p>
                                <p>
                                    <strong>Descrizione:</strong> ${prodotto.getTipologia()}
                                </p>
                                <p>
                                    <strong>Prezzo:</strong> € ${prodotto.getPrezzo()}
                                </p>
                                <p>
                                    <strong>Quantità:</strong> ${prodotto.getPezzi()} pezzi
                                </p>
                                <form class="conferma" method="post" action="cliente.html?codProdotto=${prodotto.getCod()}&conferma=ok&username=${cliente.username}">
                                    <button type="submit" name="Conferma">Conferma</button>
                                </form>
                            </div>
                        </c:when>
                        <c:otherwise>
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


                                        <c:forEach var="prodotto" items="${cliente.prodottiAcquistabili}">

                                            <c:if test="${prodotto.cod%2 != 0}">
                                                <tr class="disp">
                                            </c:if>

                                            <c:if test="${prodotto.cod%2 == 0}">
                                                <tr class="pari">        
                                             </c:if>

                                                    <td>${prodotto.nome}</td>
                                                        <!-- Immagine -->
                                                        <td><img src="M3/img/${prodotto.imgurl}" title="${prodotto.nome}" alt="${prodotto.imgurl}"></td>
                                                        <td>${prodotto.pezzi}</td>
                                                        <td>${prodotto.prezzo}</td>
                                                        <td><a href="cliente.html?codProdotto=${prodotto.getCod()}&username=${cliente.username}">${prodotto.nome}</a></td>
                                                </tr>

                                            </c:forEach>

                                    </table>          
                                </div>

                                <!-- Sidebar -->
                                <div class="sidebar">
                                    <a href="descrizione.jsp">Descrizione</a>
                                    <a href="login.jsp">Login</a>
                                </div>

                                <!-- Footer -->
                                <!--
                                <div class="footer">
                                    Footer
                                </div>
                                -->

                            </div>
                        </c:otherwise>
                    </c:choose>
            </c:when>

            <c:otherwise>
                <div>
                    <h2>Errore nel caricamento della pagina, torna indietro. </h2>
                </div>
            </c:otherwise>
        </c:choose> 
    </body>
</html>




