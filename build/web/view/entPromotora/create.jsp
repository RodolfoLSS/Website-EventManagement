<%-- 
    Document   : create
    Created on : Nov 8, 2016, 9:32:54 PM
    Author     : rodolfo
--%>

<%@page contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interface/bootstrap/css/bootstrap.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/buscaCep.js"></script>
        <title>Adicionar Entidade Promotora</title>
    </head>
    <body>
        <div class="container">
            
            <h2 class="text-center">Adicionar nova Entidade Promotora</h2>

            <c:choose>
                <c:when test="${param.acao == 'c'}">
                <form class="form-group" name="formUsuario" method="POST" action="${pageContext.servletContext.contextPath}/entpromotora/create">                    
                </c:when>
                <c:otherwise>
                <form class="form-group" name="formUsuario" method="POST" action="${pageContext.servletContext.contextPath}/entpromotora/update">                    
                    <input type="hidden" name="id" value="${u.eid}">
                </c:otherwise>
            </c:choose>

                    <label class="h4">Nome da Entidade Promotora</label>
                    <input class="form-control" type="text" name="entName" value="${u.entName}" required autofocus>
                    
                    <label class="h4">Descrição</label>
                    <input class="form-control" type="text" name="description" value="${u.description}" required>
                   
                <div class="text-center">
            <c:choose>
                <c:when test="${param.acao == 'c'}">
              
                    <button class="btn btn-lg btn-primary" type="submit">Inserir</button>
                </c:when>
                <c:otherwise>
        
                    <button class="btn btn-lg btn-primary" type="submit">Atualizar</button>
                    <a href="${pageContext.servletContext.contextPath}/entpromotora" class="btn btn-lg btn-primary" type="submit">Cancelar</a>
                </c:otherwise>
            </c:choose>


                </div>
            </form>

        </div>
                        
    </body>
</html>


