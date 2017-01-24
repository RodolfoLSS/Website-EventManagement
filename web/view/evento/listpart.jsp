<%-- 
    Document   : listpart
    Created on : Nov 24, 2016, 9:41:04 PM
    Author     : rodolfo
--%>

<%-- 
    Document   : eventoMembro
    Created on : Nov 16, 2016, 9:42:16 PM
    Author     : rodolfo
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
        <title>Participantes do evento</title>
    </head>
    <body>

        <div class="container">
            
            <table>
                <thead>
                    <th>Nome</th>
                    <th width="10px"></th>
                    <th>Data da inscricao</th>
                    <th width="10px"></th>
                    <th>Status</th>
                    <th width="10px"></th>
                </thead>
                <tbody>
                    
                <c:forEach var="u" items="${partList}">
                    <tr>
                        <td><a href="${pageContext.servletContext.contextPath}/usuario"><c:out value="${u.fullName}"/></a></td>
                        <td width="10px"></td>
                        <c:choose>
                            <c:when test="${u.fullName == "Rod"}">
                                <td>23/11/2016</td>
                            </c:when>
                            <c:otherwise>
                                <td>24/11/2016</td>
                            </c:otherwise>
                        </c:choose>
                        <td width="10px"></td>
                        <c:choose>
                            <c:when test="${u.fullName == "Jose Santos"}">
                                <td>Nao Pago</td>
                                <td width="10px"></td>
                                <td><input type="checkbox" name="" value=""> Registrar comparecimento de participante no evento<td>
                    
                            </c:when>
                            <c:when test="${u.fullName == "adm"}">
                                <td>Nao Pago</td>
                                <td width="10px"></td>
                                <td><input type="checkbox" name="" value=""> Registrar comparecimento de participante no evento<td>
                    
                            </c:when>
                            <c:otherwise>
                                <td>Pago</td>
                                <td width="10px"></td>
                                <td><input type="checkbox" name="" value="" checked> Registrar comparecimento de participante no evento<td>
                            </c:otherwise>
                        </c:choose>
                        </tr>                    
                </c:forEach>
                    
                </tbody>
            </table>
   
        </div>

    </body>
</html>


