<%-- 
    Document   : index
    Created on : Sep 29, 2016, 10:12:34 PM
    Author     : RodolfoSaldanha
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interface/bootstrap/css/bootstrap.css">
        <title>Gerenciador de Eventos</title>
    </head>
    <body>

        <div class="container">
            <form class="form-subscription" action="${pageContext.servletContext.contextPath}/usuario/create">
                <h2 class="form-subscription-heading">CADASTRE-SE</h2>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Cadastre-se</button>
            </form>
        </div>
                
        <div class="container">
            <form class="form-signin" action="${pageContext.servletContext.contextPath}/login" method="POST">
                <h2 class="form-signin-heading">Por favor, faça login.</h2>

                <label class="h4">Usuário</label><input class="form-control" type="text" name="login" placeholder="Usuário" required autofocus>
                <label class="h4">Senha</label><input class="form-control" type="password" name="pwd" placeholder="Senha" required>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
            </form>
        </div>
                
        <div class="container">
            <form class="form-signin" action="${pageContext.servletContext.contextPath}/login" method="POST">
                <h2 class="form-signin-heading">Lista de eventos Abertos</h2>
                <a href="${pageContext.servletContext.contextPath}/evento/partc">Lista de Eventos</a>
            </form>
        </div>
                        
    </body>
</html>

