<%-- 
    Document   : renda3
    Created on : Nov 25, 2016, 5:08:11 AM
    Author     : rodolfo
--%>

<%@include file="/view/include/loginCheck.jsp"%>

<%@page import="java.util.List"%>
<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interface/bootstrap/css/bootstrap.css">
        <title>Arrecadacao Total do Evento</title>
    </head>
    <body>
        <h1>Rendimento do Evento</h1>
        
                <p>Ate o momento foram um total de R$ 0 arrecadado</p>
                <p>Com o 0 inscricoes feitas</p>
                <p>E com 0 pagamentos feitos</p>
            
        
    </body>
</html>
