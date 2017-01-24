<%-- 
    Document   : index
    Created on : Sep 28, 2016, 1:49:28 AM
    Author     : RodolfoSaldanha
--%>

<%@include file="/view/include/loginCheck.jsp"%>

<%@page import="java.util.List"%>
<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath}/Interface/bootstrap/css/bootstrap.css">
        <title>Usuários</title>
    </head>
    <body>

        <div class="container">
            
            <table>
                <thead>
                    <th>Login</th>
                    <th>Nome</th>
                    <th>Ações</th>
                </thead>
                <tbody>
                    
                <c:forEach var="u" items="${usuarioList}">
                    <tr>
                        <td><c:out value="${u.login}"/></td>
                        <td><c:out value="${u.fullName}"/></td>
                        <td>
                                <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/usuario/update?id=${u.uid}" >
                                    Editar
                                </a>
                                <a class="btn btn-default link_excluir_usuario" href="${pageContext.servletContext.contextPath}/usuario/delete?id=${u.uid}">
                                    Excluir
                                </a>                            
                            
                        </td>
                    </tr>                    
                </c:forEach>
                    
                </tbody>
            </table>
           
            <a href="${pageContext.servletContext.contextPath}/logout">Logout</a>

               
            
        </div>

    </body>
</html>

