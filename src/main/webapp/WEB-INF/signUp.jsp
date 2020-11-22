<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 08.11.2020
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <form name="signupmenu" action="${pageContext.request.contextPath}/signup" method="post">
        Фамилия: <input style="margin-bottom: 5px;" type="text" name="surname" value="" /><br />
        Имя: <input style="margin-bottom: 5px;" type="text" name="name" value="" /><br />
        Отчество: <input style="margin-bottom: 5px;" type="text" name="patronymic" value="" /><br />
        Телефон: <input style="margin-bottom: 5px;" type="text" name="phone" value="" /><br />
        День рождения: <input style="margin-bottom: 5px;" type="date" name="birthday" /><br />
        Логин: <input style="margin-bottom: 5px;" type="text" name="login" value="" /><br />
        Пароль: <input style="margin-bottom: 5px;" type="password" name="password" value="" /><br />
        Повтор пароля: <input style="margin-bottom: 5px;" type="password" name="password" value="" /><br />
        <input type="submit" value="Зарегистрироваться">
    </form>
</body>
</html>
