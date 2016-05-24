<%-- 
    Document   : venditore
    Created on : 12-mag-2016, 17.35.59
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
        <title>venditore</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="keywords" content="HTML, AMAZON, e-commerce, venditore">
        <meta name="description" content="Milestone1">
        <meta name="author" content="Roberto Spiga">
        <link href="M3/style.css" rel="stylesheet" type="text/css" media="screen" />
        <base href="http://localhost:8080/AmmM1">
    </head>
    <body id="venditore">
            <c:choose>
                <c:when test="${venditore!=null}">
                    
                <!-- Container -->
                <div class="container">

                    <!-- Header -->
                    <div class="header">
                        Benvenuto ${venditore.username}
                        <c:if test="${scelta==1}">
                        <h1>Aggiungi Prodotto</h1>
                        </c:if>
                        <c:if test="${scelta==1}">
                        <h1>Modifica Prodotto</h1>
                        </c:if>
                        <c:if test="${scelta==1}">
                        <h1>Elimina Prodotto</h1>
                        </c:if>
                    </div>
                    <c:if test="${scelta == null}">
                    <div class="content">
                        <form method="POST" action="http://localhost:8080/AmmM1/VenditoreServlet"> 
                            <label for="scelta" class="labelsform">Scegli operazione</label>
                                 <select name="scelta" size="3">
                                    <option value="1">Aggiungi Prodotto</option>
                                    <option value="2">Modifica Prodotto</option>
                                    <option value="3">Elimina Prodotto</option>
                                 </select>
                            <input type="submit" name="SubmitScelta" value="Invia"/> 
                            <input type="reset" value="Reimposta"/> 
                        </form>
                    </div>    
                    </c:if>
                    <c:if test="${scelta != null}">
                        <!-- Content -->
                        <div class="content">
                            <c:if test="${scelta==1}">
                                <jsp:include page="formProdotto.jsp"/>
                            </c:if>
                            <c:if test="${scelta==2}">
                                <!--scelta id-->
                                     <select name="lista" size="3">
                                     <option value="val1">1</option>
                                     <option value="val2">2</option>
                                     <option value="val3">3</option>
                                     <option value="val4">4</option>
                                     <option value="val4">5</option>
                                     </select>
                                <jsp:include page="formProdotto.jsp"/>
                            </c:if>
                            <c:if test="${scelta==3}">
                                <form method="POST" action="VenditoreServlet"> 
                                    <label for="sceltaElimina" class="labelsform">Scegli operazione</label>
                                        <select name="sceltaElimina" size="3">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>                                        
                                            <option value="5">5</option>
                                        </select>
                                <input type="submit" name="SubmitElimina" value="Invia"/> 
                                <input type="reset" value="Reimposta"/> 
                            </form>
                            </c:if>
                        </div>
                    </c:if>
                    <!-- Sidebar -->
                    <div class="sidebar">
                        <a href="descrizione.jsp">Descrizione</a>
                        <a href="../login.jsp">Login</a>
                    </div>

                    <!-- Footer -->
                    <!--
                    <div class="footer">
                        Footer
                    </div>
                    -->

                </div>
            </c:when>

            <c:otherwise>
                <div>
                    <h2>Errore nel caricamento della pagina, torna indietro. </h2>
                </div>
            </c:otherwise>
        </c:choose>            
    </body>
</html>

