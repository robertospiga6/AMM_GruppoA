<%-- 
    Document   : formProdotto
    Created on : 24-mag-2016, 17.33.43
    Author     : Robi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body><div class="labels">
                            <label for="nome" class="labelsform">Nome oggetto</label> 
                            <label for="file" class="labelsform">Immagine Prodotto </label>
                            <label for="tAdescrizione" class="labelsform">Descrizione Oggetto</label>
                            <label for="prezzo" class="labelsform2">Prezzo Oggetto</label> 
                            <label for="disponibili" class="labelsform">Quantit&agrave; Disponibile</label>
                        </div>
        <div class="inputs">
        <form method="POST" action="VenditoreServlet"> 
            <input type="text" name="Nome" id="nome" class="notButton">
            <input type="file" name="ImgUrl" id="file" class="notButton"/>
            <textarea name="Tipologia" id="tAdescrizione" cols="40" rows="10" class="notButton">Descrizione Oggetto</textarea>
            <input type="number" name="Prezzo" id="prezzo" class="notButton">
            <input type="number" name="Pezzi" id="disponibili" class="notButton">
            <input type="submit" name="SubmitForm" value="Invia"/> 
            <input type="reset" value="Reimposta"/> 
        </form></div>
    </body>
</html>
