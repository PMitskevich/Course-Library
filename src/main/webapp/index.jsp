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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="theme-color" content="#563d7c">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        div.content {
            width: 30%;
        }
    </style>
</head>
<body>
    <div class="content">
        <form name="authorization" action="${pageContext.request.contextPath}/authorization" method="post">
            <label for="login" class="sr-only">Логин</label>
            <input id="login" class="form-control" type="text" name="login" placeholder="Логин" value="" required autofocus /><br />
            <label for="password" class="sr-only">Пароль</label>
            <input id="password" class="form-control" type="password" name="password" placeholder="Пароль" value="" required /><br />
            <input type="submit" class="btn btn-lg btn-primary btn-block" value="Авторизироваться">
        </form>
        <form name="signup" action="${pageContext.request.contextPath}/signup" method="get">
            <input type="submit" class="btn btn-lg btn-primary btn-block" value="Зарегистрироваться">
        </form>
    </div>
</body>
</html>
