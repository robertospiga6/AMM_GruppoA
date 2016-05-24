<%-- 
    Document   : descrizione
    Created on : 12-mag-2016, 17.35.38
    Author     : Robi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>descrizione</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="keywords" content="HTML, AMAZON, e-commerce">
        <meta name="description" content="Milestone1">
        <meta name="author" content="Roberto Spiga">
        <link href="M3/style.css" rel="stylesheet" type="text/css" media="screen" />
    </head>
    <body id="descrizione">
        <!-- Container -->
        <div class="container">
            
            <!-- Header -->
            <div class="header">
                <c:if test="${venditore.username == ""}">
                Benvenuto ${cliente.username}
                </c:if>
                <c:if test="${cliente.username == ""}">
                Benvenuto ${venditore.username}  
                </c:if>
                <h1>AMAZON</h1>
            </div>
            
            <!-- Content -->
            <div class="content">
                    <ul id="sommario">
                        <li><a href="#prodotti">Prodotti</a></li>
                        <li><a href="#6venditore">Venditore</a></li>
                        <li><a href="#6cliente">Cliente</a></li>
                        <li><a href="#navbar">Login</a></li>
                    </ul>
                    <h2 id="prodotti">I nostri prodotti</h2>
                    <!-- Paragrafo -->
                    <p>
                        Auto e Moto
                        <br>Casa e cucina
                        <br>Commercio, Industria e Scienza
                        <br>Elettronica
                        <br>Film e TV
                        <br>Illuminazione
                        <br>Informatica
                        <br>Kindle Store
                        <br>Musica Digitale
                        <br>Software
                        <br>Strumenti musicali e DJ
                        <br>Videogiochi
                    </p>
                    <h3 id="6venditore">Sei un venditore?</h3>
                    <p>
                        Vendere su Amazon è un servizio che consente ai venditori 
                        di mettere in vendita i propri prodotti su Amazon.it e sugli 
                        altri marketplace europei di Amazon.<br>
                        I tuoi prodotti sono più facili da trovare e acquistare su Amazon<br>
                        Il tuo marchio è visibile a milioni di clienti Amazon<br>
                        Puoi vendere sui cinque siti di Amazon in Europa<br>
                        Puoi organizzare facilmente l'inventario grazie a un account unico, comune a tutti i marketplace europei di Amazon<br>
                        Puoi trarre vantaggio dalla comprovata esperienza commerciale di Amazon<br>
                        Puoi usufruire dei sistemi di sicurezza e protezione antifrode di Amazon<br>
                    </p>
                    <h3 id="6cliente">Sei un cliente?</h3>
                    <p>
                        Amazon è una delle più grandi aziende di 
                        commercio elettronico statunitense con sede a Seattle, nello stato 
                        di Washington, Stati Uniti d'America.<br>
                        Potrai trovare qualsiasi prodotto che abbia a che fare con l'elettronica nel nostro sito! Che aspetti?!
                    </p>
            </div>
            
            <!-- Sidebar -->
            <div class="sidebar">
                <a href="M3/login.jsp">Login</a>
            </div>
            
            <!-- Footer -->
            <!--
            <div class="footer">
                Footer
            </div>
            -->
            
        </div>    
    </body>
</html>
