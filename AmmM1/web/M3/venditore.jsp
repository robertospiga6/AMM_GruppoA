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
        
    </head>
    <body id="venditore">
            <c:choose>
                <c:when test="${venditore!=null}">
                    
                <!-- Container -->
                <div class="container">

                    <!-- Header -->
                    <div class="header">
                        Benvenuto ${venditore.username}
                        <form method="post" action="login.html">
                            <button type="submit" name="LogOut">Esci</button>
                        </form> 
                        <c:if test="${scelta==1}">
                        <h1>Aggiungi Prodotto</h1>
                        </c:if>
                        <c:if test="${scelta==2}">
                        <h1>Modifica Prodotto</h1>
                        </c:if>
                        <c:if test="${scelta==3}">
                        <h1>Elimina Prodotto</h1>
                        </c:if>
                    </div>
                    <c:if test="${scelta == null}">
                    <div class="content">
                        <form method="POST" action="venditore.html?id=${venditore.id}"> 
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
                                <div class="labels">
                                    <jsp:include page='labelsV.jsp'/>
                                </div>
                                <div class="inputs">
                                <form method="POST" action="venditore.html?id=${venditore.id}"> 
                                    
                                    <jsp:include page='inputsV.jsp'/>
                                    <input type="submit" name="SubmitAggiungi" value="Invia"/> 
                                    <input type="reset" value="Reimposta"/> 
                                </form>
                                </div>
                            </c:if>
                            <c:if test="${scelta==2}">
                                <div class="labels">
                                    <label for="oggetto" class="labelsform">Seleziona oggetto</label> 
                                    <jsp:include page='labelsV.jsp'/>
                                </div>
                                <div class="inputs">
                                    <form method="POST" action="venditore.html?id=${venditore.id}">
                                        <select class="notButton" name="Cod">
                                            <c:forEach var="prodotto" items="${venditore.prodottiInVendita}">
                                                <option value="${prodotto.cod}">${prodotto.nome}</option>
                                            </c:forEach>
                                        </select>
                                        <jsp:include page='inputsV.jsp'/>
                                        <input type="submit" name="SubmitModifica" value="Invia"/> 
                                        <input type="reset" value="Reimposta"/> 
                                    </form>
                                </div>
                            </c:if>
                            <c:if test="${scelta==3}">
                                <form method="POST" action="venditore.html?id=${venditore.id}"> 
                                    <p>
                                    <label for="sceltaElimina" class="labelsform">Che prodotto vuoi eliminare?</label>
                                    
                                        <select name="Cod">
                                            <c:forEach var="prodotto" items="${venditore.prodottiInVendita}">
                                                <option value="${prodotto.cod}">${prodotto.nome}</option>
                                            </c:forEach>
                                        </select>
                                    <input type="submit" name="SubmitElimina" value="Invia"/> 
                                    <input type="reset" value="Reimposta"/> 
                                    </p>
                                </form>
                            </c:if>
                        </div>
                    </c:if>
                    <!-- Sidebar -->
                    <jsp:include page="sidebarV.jsp" />
                </div>
            </c:when>

            <c:otherwise>
                    <h2>Errore accesso negato, torna indietro. </h2>
                
                <a href="login.html">Login</a>
            </c:otherwise>
        </c:choose>            
    </body>
</html>

