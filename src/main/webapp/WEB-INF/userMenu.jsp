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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="theme-color" content="#563d7c">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Пользовательское меню</title>
    <style>
        .content {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            flex: 1 1 auto;
        }

        input {
            width: 250px;
        }

        footer {
            background-color: #C0C0C0;
        }

        .flex-footer {
            display: flex;
            justify-content: center;
            align-items: center;
            color: antiquewhite;
        }
    </style>
</head>
<body class="d-flex flex-column h-100">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="#">Меню пользователя</a>
        <div class="collapse-navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">К авторизации<span class="sr-only"></span></a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="content">
        <form name="order" action="${pageContext.request.contextPath}/choosebook" method="get">
            <input type="submit" class="btn btn-primary" value="Забронировать книгу" />
        </form>
        <form name="order" action="${pageContext.request.contextPath}/cancelorder" method="get">
            <input type="submit" class="btn btn-primary" value="Отменить бронь книги" />
        </form>
        <form name="showconfirmed" action="${pageContext.request.contextPath}/showconfirmed" method="get">
            <input type="submit" class="btn btn-primary" value="Подтверждённые книги" />
        </form>
        <form name="showunconfirmed" action="${pageContext.request.contextPath}/showunconfirmed" method="get">
            <input type="submit" class="btn btn-primary" value="Неподтверждённые книги" />
        </form>
    </div>
    <footer class="footer mt-auto py-3">
        <div class="flex-footer">
            <span>Минск, 2020</span>
        </div>
    </footer>
</body>
</html>
