<%-- 
    Document   : welcome
    Created on : Sep 29, 2016, 10:04:11 PM
    Author     : RodolfoSaldanha
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
        <title>Espaço do Usuário</title>
    </head>
    <body>

        <div class="container">
            <h1>Bem-vindo, <c:out value="${usuarioLogado.fullName}"/>!</h1>
            <p>
                <a href="${pageContext.servletContext.contextPath}/usuario">Atualizar Informações</a>
                <a href="${pageContext.servletContext.contextPath}/logout">Logout</a>
            </p>
        </div>
    </body>
</html>
