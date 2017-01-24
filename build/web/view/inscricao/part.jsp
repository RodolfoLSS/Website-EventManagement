<%-- 
    Document   : part
    Created on : Nov 25, 2016, 3:48:01 AM
    Author     : rodolfo
--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interface/bootstrap/css/bootstrap.css">
        <title>Inscricao Participante em evento</title>
    </head>
    <body>
        <div class="container">
            
            <h2 class="text-center">Cadastro em evento</h2>

            <c:choose>
                <c:when test="${param.acao == 'c'}">
                <form class="form-group" name="formUsuario" action="${pageContext.servletContext.contextPath}/evento/membro">                    
                </c:when>
                <c:otherwise>
                <form class="form-group" name="formUsuario" action="${pageContext.servletContext.contextPath}/evento/membro">                    
                    <input type="hidden" name="fk_eventid" value="${u.eventid}">
                </c:otherwise>
            </c:choose>
                    <label class="h4">Nome do Usuario que deseja adicionar</label>
                    <input class="form-control" type="text" name="fullName" value="${u.fullName}" required autofocus>
                    
                    <label class="h4">Forma de pagamento</label>
                    <input class="form-control" type="text" name="payment" value="${u.payment}" required autofocus>
                    
                    <label class="h4">Como ficou sabendo do evento?</label>
                    <input class="form-control" type="text" name="getToKnowEvent" value="${u.getToKnowEvent}" required>
                    
                    <label class="h4">Estará participando com outras pessoas?</label>
                    <input class="form-control" type="text" name="descriptiveField" value="${u.descriptiveField}" required>                 
                    
                    <input type="checkbox" name="" value=""> Registrar pagamento 
                <div class="text-center">
            <c:choose>
                <c:when test="${param.acao == 'c'}">
         
                    <button class="btn btn-lg btn-primary" type="submit">Confirmar inscrição</button>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.servletContext.contextPath}/evento/membro" class="btn btn-lg btn-primary" type="submit">Cancelar Inscricao</a>
                </c:otherwise>
            </c:choose>


                </div>
            </form>

        </div>
                        
    </body>
</html>
