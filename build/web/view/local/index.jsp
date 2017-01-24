<%-- 
    Document   : index
    Created on : Nov 15, 2016, 9:29:27 PM
    Author     : rodolfo
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
        <title>Local</title>
    </head>
    <body>

        <div class="container">
            
            <table>
                <thead>
                    <th>Local</th>
                    <th>Descricao</th>
                    <th>Ações</th>
                </thead>
                <tbody>
                    
                <c:forEach var="u" items="${localList}">
                    <tr>
                        <td><c:out value="${u.placeName}"/></td>
                        <td><c:out value="${u.telephone}"/></td>
                        <td>
                                <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/local/create?id=${u.lid}" >
                                    Editar
                                </a>
                                <a class="btn btn-default link_excluir_usuario" href="${pageContext.servletContext.contextPath}/local/delete?id=${u.lid}">
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
