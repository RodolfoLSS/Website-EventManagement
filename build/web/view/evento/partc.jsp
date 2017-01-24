<%-- 
    Document   : partc
    Created on : Oct 12, 2016, 6:16:44 PM
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
        <title>Eventos</title>
    </head>
    <body>

        <div class="container">
            
            <table>
                <thead>
                    <th>Evento</th>
                    <th>Descricao</th>
                    <th>Ações</th>
                </thead>
                <tbody>
                    
                <c:forEach var="u" items="${eventoList}">
                    <tr>
                        <td><c:out value="${u.eventName}"/></td>
                        <td><c:out value="${u.description}"/></td>
                        <td>    
                                <a class="btn btn-default link_excluir_usuario" href="${pageContext.servletContext.contextPath}/view/inscricao/index.jsp?id=${u.eventid}">
                                    Inscrever-se
                                </a>  
                                <a class="btn btn-default link_excluir_usuario" href="${pageContext.servletContext.contextPath}/usuario/delete?id=${u.eventid}">
                                    Excluir
                                </a>                            
                            
                        </td>
                    </tr>                    
                </c:forEach>
                    
                </tbody>
            </table>
   
        </div>

    </body>
</html>
