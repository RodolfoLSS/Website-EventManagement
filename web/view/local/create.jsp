<%-- 
    Document   : create
    Created on : Nov 8, 2016, 9:32:42 PM
    Author     : rodolfo
--%>

<%@page contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interface/bootstrap/css/bootstrap.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/buscaCep.js"></script>
        <title>Inserir Local</title>
    </head>
    <body>
        <div class="container">
            
            <h2 class="text-center">Inserção de um novo local</h2>

            <c:choose>
                <c:when test="${param.acao == 'c'}">
                <form class="form-group" name="formUsuario" method="POST" action="${pageContext.servletContext.contextPath}/place/create">                    
                </c:when>
                <c:otherwise>
                <form class="form-group" name="formUsuario" method="POST" action="${pageContext.servletContext.contextPath}/place/update">                    
                    <input type="hidden" name="id" value="${u.lid}">
                </c:otherwise>
            </c:choose>

                    <label class="h4">Nome do Local</label>
                    <input class="form-control" type="text" name="placeName" value="${u.placeName}" required autofocus>
                    
                    <label class="h4">CEP</label>
                    <input class="form-control" type="text" name="cep" value="${u.cep}" required onblur="pesquisacep(this.value) " placeholder="Ex.:00000-000" id="cep">
                    
                    <label class="h4">Logradouro</label>
-                   <input class="form-control" type="text" name="logradouro" value="${u.logradouro}" required id="rua">
-                    
-                   <label class="h4">Complemento</label>
-                   <input class="form-control" type="text" name="complemento" value="${u.complemento}" required>
-                    
-                   <label class="h4">Bairro</label>
-                   <input class="form-control" type="text" name="bairro" value="${u.bairro}" required id="bairro">

                    <label class="h4">Cidade</label>
                    <input class="form-control" type="text" name="city" value="${u.city}" required id="cidade">
                    
                    <label class="h4">Estado</label>
                    <input class="form-control" type="text" name="state" value="${u.state}" required id="uf">
                    
                    <label class="h4">Telefone</label>
                    <input class="form-control" type="text" name="telephone" value="${u.telephone}" placeholder="(DDD)XXXXXXXX" required>
                    
                    <label class="h4">Coordenadas</label>
                    <input class="form-control" type="text" name="coordenates" value="${u.coordenades}" required>

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