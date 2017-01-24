<%-- 
    Document   : eventoMembro
    Created on : Nov 16, 2016, 9:42:16 PM
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
        <title>Eventos</title>
    </head>
    <body>

        <div class="container">
            
            <table>
                <thead>
                    <th>Evento</th>
                    <th width="10px"></th>
                    <th>Entidade Promotora</th>
                    <th width="10px"></th>
                    <th>Data de Inicio</th>
                    <th width="10px"></th>
                    <th>Data de Fim</th>
                    <th width="10px"></th>
                    <th>Status</th>
                    <th width="10px"></th>
                </thead>
                <tbody>
                    
                <c:forEach var="u" items="${eventoList}">
                    <tr>
                        
                        <td><input type="checkbox"><c:out value="${u.eventName}"/></td>
                        <td width="10px"></td>
                        <c:choose>
                            <c:when test="${u.fk_entPromotora_evento == 1}">
                                <td>Euphoria</td>
                            </c:when>
                            <c:when test="${u.fk_entPromotora_evento == 2}">
                                <td>Mamute Eventos</td>
                            </c:when>
                            <c:when test="${u.fk_entPromotora_evento == 4}">
                                <td>Gorilada</td>
                            </c:when>
                            <c:otherwise>
                                <td>Eventos Londrina</td>
                            </c:otherwise>
                        </c:choose>
                        <td width="10px"></td>
                        <td><c:out value="${u.beginDate}"/></td>
                        <td width="10px"></td>
                        <td><c:out value="${u.endDate}"/></td>
                        <td width="10px"></td>
                        <c:choose>
                            <c:when test="${u.fk_entPromotora_evento == 4}">
                                <td>Passado</td>
                            </c:when>
                            <c:when test="${u.fk_entPromotora_evento == 15}">
                                <td>Passado</td>
                            </c:when>
                            <c:otherwise>
                                <td>Ativo</td>
                            </c:otherwise>
                        </c:choose>
                        <td width="10px"></td>
                        <td>
                                <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/evento/create?id=${u.eventid}" >
                                    Editar
                                </a>
                                <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/evento/listpart?id=${u.eventid}" >
                                    Lista de Participantes
                                </a>
                                <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/view/inscricao/part.jsp?id=${u.eventid}" >
                                    Inscrever Participante
                                </a>
                                <c:choose>
                                    <c:when test="${u.eventid == 50}">
                                        <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/view/evento/renda.jsp?id=${u.eventid}" >
                                            Renda
                                        </a>
                                    </c:when>
                                    <c:when test="${u.eventid == 30}">
                                        <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/view/evento/renda2.jsp?id=${u.eventid}" >
                                            Renda
                                        </a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-default" href="${pageContext.servletContext.contextPath}/view/evento/renda3.jsp?id=${u.eventid}" >
                                            Renda
                                        </a>
                                    </c:otherwise>
                                </c:choose>
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

