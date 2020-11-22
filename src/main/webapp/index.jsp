<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 03.11.2020
  Time: 1:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
    <h1>Меню авторизации</h1>
    <form name="authorization" action="${pageContext.request.contextPath}/authorization" method="post">
        Логин: <input style="margin-bottom: 5px;" type="text" name="login" value="" /><br />
        Пароль: <input style="margin-bottom: 5px;" type="password" name="password" value="" /><br />
        <input type="submit" value="Авторизироваться">
    </form>
    <form name="signup" action="${pageContext.request.contextPath}/signup" method="get">
        <input type="submit" value="Зарегистрироваться">
    </form>
</body>
</html>
