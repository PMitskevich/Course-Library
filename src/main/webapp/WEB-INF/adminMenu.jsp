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
    <title>Меню администратора</title>
</head>
<body>
    <h1>Меню администратора</h1>
    <form name="order" action="${pageContext.request.contextPath}/showorders" method="get">
        <input type="submit" value="Посмотреть заказы" />
    </form>
    <form name="order" action="${pageContext.request.contextPath}/" method="get">
        <input type="submit" value="Заказать новую книгу" />
    </form>
    <form name="order" action="${pageContext.request.contextPath}/showorders" method="get">
        <input type="submit" value="" />
    </form>
    <a href="${pageContext.request.contextPath}/index.jsp">Вернуться к авторизации</a>
</body>
</html>
