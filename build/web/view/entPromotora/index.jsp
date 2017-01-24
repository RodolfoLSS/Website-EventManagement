<%-- 
    Document   : index
    Created on : Nov 8, 2016, 11:18:06 PM
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
        <title>Entidade Promotora</title>
    </head>
    <body>

        <div class="container">
            
            <table>
                <thead>
                    <th>Entidade Promotora</th>
                    <th>Descricao</th>
                    <th>Ações</th>
                </thead>
                <tbody>
                    
                <c:forEach var="u" items="${entPromotoraList}">
                    <tr>
                        <td><c:out value="${u.entName}"/></td>
                        <td><c:out value="${u.description}"/></td>
                        <td>
                                <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/evento/create?id=${u.eid}" >
                                    Editar
                                </a>
                                <a class="btn btn-default link_excluir_usuario" href="${pageContext.servletContext.contextPath}/usuario/delete?id=${u.eid}">
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
