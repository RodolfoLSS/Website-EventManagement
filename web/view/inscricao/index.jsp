<%-- 
    Document   : index
    Created on : Oct 12, 2016, 6:06:49 PM
    Author     : RodolfoSaldanha
--%>

<%@page contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interface/bootstrap/css/bootstrap.css">
        <title>Inscricao em Evento</title>
    </head>
    <body>
        <div class="container">
            
            <h2 class="text-center">Cadastro em evento</h2>

            <c:choose>
                <c:when test="${param.acao == 'c'}">
                <form class="form-group" name="formUsuario" method="POST" action="${pageContext.servletContext.contextPath}/inscricao/add">                    
                </c:when>
                <c:otherwise>
                <form class="form-group" name="formUsuario" method="POST" action="${pageContext.servletContext.contextPath}/evento/update">                    
                    <input type="hidden" name="fk_eventid" value="${u.eventid}">
                </c:otherwise>
            </c:choose>

                    <label class="h4">Forma de pagamento</label>
                    <input class="form-control" type="text" name="payment" value="${u.payment}" required autofocus>
                    
                    <label class="h4">Como ficou sabendo do evento?</label>
                    <input class="form-control" type="text" name="getToKnowEvent" value="${u.getToKnowEvent}" required>
                    
                    <label class="h4">Estará participando com outras pessoas?</label>
                    <input class="form-control" type="text" name="descriptiveField" value="${u.descriptiveField}" required>                 

                <div class="text-center">
            <c:choose>
                <c:when test="${param.acao == 'c'}">
         
                    <button class="btn btn-lg btn-primary" type="submit">Confirmar inscrição</button>
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


