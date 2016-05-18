<%-- 
    Document   : login
    Created on : 12-mag-2016, 17.35.47
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
        <title>login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="keywords" content="HTML, AMAZON, e-commerce, login">
        <meta name="description" content="Milestone1">
        <meta name="author" content="Roberto Spiga">
        <link href="style.css" rel="stylesheet" type="text/css" media="screen" />
    </head>
    <body id="login">
        <!-- Container -->
        <div class="container">
            
            <!-- Header -->
            <div class="header">
                <h1>Login</h1>
            </div>
            
            <!-- Content -->
            <div class="content">
                <div class="labels">
                    <label for="username">Username</label>
                    <label for="password">Password</label>
                </div>
                <div class="inputs">
                    <form method="POST"> 
                        <input class="notButton" type="text" name="Username" id="username">
                        <input type="password" name="Password" id="password">
                        <input type="Submit" value="Accedi"/>
                    </form>
                </div>
            </div>
            
            <!-- Sidebar -->
            <div class="sidebar">
                <a href="descrizione.html">Descrizione</a>
                <a href="cliente.html">Cliente</a>
                <a href="venditore.html">Venditore</a>
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