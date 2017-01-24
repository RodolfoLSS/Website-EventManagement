<%-- 
    Document   : welcomeMembro
    Created on : Oct 10, 2016, 10:48:58 AM
    Author     : RodolfoSaldanha
--%>

<%@include file="/view/include/loginCheck.jsp"%>

<%@page import="java.util.List"%>
<%@page import="model.Evento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interface/bootstrap/css/bootstrap.css">
        <title>Espaço do Usuário - Membro</title>
    </head>
    <body>

        <div class="container">
            <h1>Bem-vindo, <c:out value="${usuarioLogado.fullName}"/>!</h1>
            <p>
                <div class="container">
                    <form class="form-subscription" action="${pageContext.servletContext.contextPath}/evento/create">
                    
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Crie Evento</button>
                    </form>
                </div>
                <a href="${pageContext.servletContext.contextPath}/evento/membro">Lista de Eventos</a>
            </p>
            
            <p>
                <div class="container">
                    <form class="form-subscription" action="${pageContext.servletContext.contextPath}/usuario/create">
                    
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Crie Usuario</button>
                    </form>
                </div>
                <a href="${pageContext.servletContext.contextPath}/usuario">Lista de Usuarios</a>
            </p>
            
            <p>
                <div class="container">
                    <form class="form-subscription" action="${pageContext.servletContext.contextPath}/place/create" method="POST">
                    
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Adicione Local</button>
                    </form>
                </div>
                <a href="${pageContext.servletContext.contextPath}/place">Atualizar Local</a>
            </p>
            
            <p>
                <div class="container">
                    <form class="form-subscription" action="${pageContext.servletContext.contextPath}/entpromotora/create" method="POST">
                    
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Adicione Entidade Promotora</button>
                    </form>
                </div>
                <a href="${pageContext.servletContext.contextPath}/entpromotora">Atualizar Entidade Promotora</a>
            </p>
            
            <p>
                <a href="${pageContext.servletContext.contextPath}/logout">Logout</a>
            </p>
        </div>
    </body>
</html>
