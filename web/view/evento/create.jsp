<%-- 
    Document   : create
    Created on : Oct 10, 2016, 9:10:16 AM
    Author     : RodolfoSaldanha
--%>

<%@page contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interface/bootstrap/css/bootstrap.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/buscaCep.js"></script>
        <title>Inserir Evento</title>
    </head>
    <body>
        <div class="container">
            
            <h2 class="text-center">Inserção de um novo evento</h2>

            <c:choose>
                <c:when test="${param.acao == 'c'}">
                <form class="form-group" name="formUsuario" method="POST" action="${pageContext.servletContext.contextPath}/evento/create">                    
                </c:when>
                <c:otherwise>
                <form class="form-group" name="formUsuario" method="POST" action="${pageContext.servletContext.contextPath}/evento/update">                    
                    <input type="hidden" name="id" value="${u.eventid}">
                </c:otherwise>
            </c:choose>

                    <label class="h4">Nome do Evento</label>
                    <input class="form-control" type="text" name="eventName" value="${u.eventName}" required autofocus>
                    
                    <label class="h4">Descrição</label>
                    <input class="form-control" type="text" name="description" value="${u.description}" required>
                    
                    <label class="h4">Informações Importantes</label>
                    <input class="form-control" type="text" name="impInformation" value="${u.impInformation}" required>
                    
                    <label class="h4">Preço</label>
                    <input class="form-control" type="text" name="price" value="${u.price}" required>
                    
                    <label class="h4">Data de inicio</label>
                    <input class="form-control" type="text" name="beginDate" value="${u.beginDate}" required>
                    
                    <label class="h4">Data de fim</label>
                    <input class="form-control" type="text" name="endDate" value="${u.endDate}" required>
                    
                    <label class="h4">Hora de inicio</label>
                    <input class="form-control" type="text" name="beginTime" value="${u.beginTime}" required>
                    
                    <label class="h4">Hora de fim</label>
                    <input class="form-control" type="text" name="endTime" value="${u.endTime}" required>
                    
                    <label class="h4">Dias da semana</label>
                    <input class="form-control" type="text" name="weekdays" value="${u.weekdays}" required>
                    
                    <label class="h4">Local</label>
                    <input class="form-control" type="text" name="place" value="${u.place}" required>
                    
                    <label class="h4">Entidade Promotora</label>
                    <input class="form-control" type="text" name="entPromotora" value="${u.entPromotora}" required>
                    
                    <label class="h4">Senha do Usuario</label>
                    <input class="form-control" type="password" name="pwd" value="${u.pwd}"> 
                           
                    <label class="h4">Tipo do evento</label>
                    <select name="eventType">
                        <option value='1' ${u.eventType == 1 ? 'selected' : ''}>Unico</option>
                        <option value='2' ${u.eventType == 2 ? 'selected' : ''}>Multiplo</option>
                        <option value='3' ${u.eventType == 3 ? 'selected' : ''}>Periodico</option>
                    </select>
                 

                <div class="text-center">
            <c:choose>
                <c:when test="${param.acao == 'c'}">
              
                    <button class="btn btn-lg btn-primary" type="submit">Inserir</button>
                </c:when>
                <c:otherwise>
        
                    <button class="btn btn-lg btn-primary" type="submit">Atualizar</button>
                    <a href="${pageContext.servletContext.contextPath}/evento" class="btn btn-lg btn-primary" type="submit">Cancelar</a>
                </c:otherwise>
            </c:choose>


                </div>
            </form>

        </div>
                        
    </body>
</html>

