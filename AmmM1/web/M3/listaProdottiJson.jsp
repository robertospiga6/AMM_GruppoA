<%-- 
    Document   : listaProdottiJson
    Created on : 9-giu-2016, 17.45.35
    Author     : Robi
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<json:array>    
    <c:forEach var="prodotto" items="${listaProdotti}">
        <json:object>
            <json:property name="cod" value="${prodotto.getCod()}"/>
            <json:property name="nome" value="${prodotto.getNome()}"/>
            <json:property name="imgurl" value="${prodotto.getImgurl()}"/>
            <json:property name="pezzi" value="${prodotto.getPezzi()}"/>
            <json:property name="prezzo" value="${prodotto.getPrezzo()}"/>
            <json:property name="idCliente" value="${idCliente}"/>
        </json:object>
    </c:forEach>
</json:array> 