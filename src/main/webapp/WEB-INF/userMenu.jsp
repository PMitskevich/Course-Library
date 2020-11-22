<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.11.2020
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Пользовательское меню</title>
    <style>
        a {
            text-decoration: black;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h1>Меню пользователя</h1>
    <form name="order" action="${pageContext.request.contextPath}/choosebook" method="get">
        <input type="submit" value="Забронировать книгу" />
    </form>
    <form name="showconfirmed" action="${pageContext.request.contextPath}/showconfirmed" method="get">
        <input type="submit" value="Подтверждённые книги" />
    </form>
    <form name="showunconfirmed" action="${pageContext.request.contextPath}/showunconfirmed" method="get">
        <input type="submit" value="Неподтверждённые книги" />
    </form>
    <a href="${pageContext.request.contextPath}/index.jsp">Вернуться к авторизации</a>
</body>
</html>
