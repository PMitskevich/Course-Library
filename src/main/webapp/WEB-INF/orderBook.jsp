<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.11.2020
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Выбор книг</title>
</head>
<body>
    <h1>Меню оформления заказа</h1>
    <jsp:useBean id="books" scope="request" type="java.util.List"/>
    <jsp:useBean id="authors" scope="request" type="java.util.List"/>
    <form name="choosebook" action="${pageContext.request.contextPath}/orderbook" method="get">
        <c:forEach var="book" items="${books}">
            <input type="hidden" name="id" value="${book.bookId}" />
            <input type="submit" value="${book.name}" />
<%--            <c:forEach var="author" items="${authors}">--%>
<%--                <c:if test="${author.authorId == book.authorId}">--%>
<%--                </c:if>--%>
<%--            </c:forEach>--%>
        </c:forEach>
    </form>
<%--    <jsp:scriptlet>response.sendRedirect("${pageContext.request.contextPath}/WEB-INF/userMenu.jsp")</jsp:scriptlet>--%>
<%--    <a href="${pageContext.request.contextPath}/index.jsp">Вернуться в меню пользователя</a>--%>
<%--    <jsp:forward page="userMenu.jsp">--%>
<%--        <input type="button" value="Кнопка" />--%>
<%--    </jsp:forward>--%>
</body>
</html>
