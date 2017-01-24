<%-- 
    Document   : loginCheck
    Created on : Sep 29, 2016, 10:00:33 PM
    Author     : RodolfoSaldanha
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty sessionScope.usuarioLogado}">
    <c:redirect context="${pageContext.servletContext.contextPath}" url="/"/>
</c:if>
