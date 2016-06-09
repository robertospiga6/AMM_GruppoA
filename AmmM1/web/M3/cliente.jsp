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
        <base href="http://localhost:8080/AmmM1/">
    </head>
    <body id="cliente">
        <c:choose>
            <c:when test="${cliente!=null}">
                <c:choose>
                    <c:when test="${saldoInsuff}">
                        <div>
                            <h2>Errore credito insufficiente</h2>
                        </div>      
                        <jsp:include page='sidebarC.jsp'/>
                    </c:when>
                    <c:when test="${conferma}">
                        <div>
                            <h2>Acquisto confermato</h2>
                        </div>       
                        <jsp:include page='sidebarC.jsp'/>
                    </c:when>
                    <c:when test="${riepilogo}">
                        <div id="resoconto">
                            <p>Nome prodotto : ${prodotto.nome}</p>
                            <p>Tipologia prodotto : ${prodotto.tipologia}</p>
                            <p>Prezzo prodotto : ${prodotto.prezzo}</p>
                            <p>Pezzi prodotto : ${prodotto.pezzi}</p>
                        </div>
                        <form class="conferma" method="post" action="cliente.html?codProdotto=${prodotto.getCod()}&conferma=ok&id=${cliente.id}">
                            <p>Quanti pezzi vuoi acquistarne?
                                <select name="NumeroPezzi" >
                                    <option value="1" selected="selected"> 1 </option>
                                    <c:forEach begin="2" end="${prodotto.getPezzi()}" var="n" >
                                        <option value="${n}"> ${n} </option>
                                    </c:forEach>
                                </select>
                            </p>
                            <button type="submit" name="Conferma">Conferma</button>
                        </form>
                        <jsp:include page='sidebarC.jsp'/>
                    </c:when>
                    <c:otherwise>
                        <!-- Container -->
                        <div class="container">

                            <!-- Header -->
                            <div class="header">
                                Benvenuto ${cliente.username}
                                <h1>Prodotti</h1>
                                <form method="post" action="login.html">
                                    <button type="submit" name="LogOut">Esci</button>
                                </form> 
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
                                                <td><a href="cliente.html?codProdotto=${prodotto.getCod()}&id=${cliente.id}">${prodotto.nome}</a></td>
                                            </tr>
                                    </c:forEach>
                                </table>          
                            </div>

                            <!-- Sidebar -->
                            <div class="sidebar">        
                                <jsp:include page='sidebarC.jsp'/>
                                <c:if test="${ricaricaFatta}">
                                    <p>
                                        Ricarica Effettuata
                                    </p>
                                </c:if>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:when>

            <c:otherwise>
                    <h2>Errore accesso negato, torna indietro. </h2>
                 
                <a href="login.html">Login</a>
            </c:otherwise>
        </c:choose> 
    </body>
</html>




