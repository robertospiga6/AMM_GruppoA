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
        
        <!-- jQuery -->
        <script type="text/javascript" src="M3/js/jquery-2.2.4.min.js"></script>
        <script type="text/javascript" src="M3/js/javascript.js"></script>
    
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
                                Benvenuto ${cliente.getUsername()}
                                <h1>Prodotti</h1>
                                <form method="post" action="login.html">
                                    <button type="submit" name="LogOut">Esci</button>
                                </form>
                                <label for="ricerca">Filtra</label>
                                <input type="text" id="ricerca" size="15"/>
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
                                    <c:set var="nR" value="0" scope="page" />
                                    <c:forEach var="prodotto" items="${listaProdotti}">
                                        <c:choose>
                                            <c:when test="${nR%2==0}" >
                                                <tr class="pari">
                                            </c:when>
                                            <c:otherwise>
                                                <tr class="disp">
                                            </c:otherwise>
                                        </c:choose>
                                                <td>${prodotto.getNome()}</td>
                                                <!-- Immagine -->
                                                <td><img src="M3/img/${prodotto.getImgurl()}" title="${prodotto.getNome()}" alt="${prodotto.getImgurl()}"></td>
                                                <td>${prodotto.getPezzi()}</td>
                                                <td>${prodotto.getPrezzo()}</td>
                                                <td><a href="cliente.html?id=${cliente.getId()}&codProdotto=${prodotto.getCod()}">${prodotto.getNome()}</a></td>
                                            </tr>
                                            <c:set var="nR" value="${riga+1}" scope="page"/>
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




