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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="theme-color" content="#563d7c">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Выбор книг</title>
    <style>
        .books {
            display: flex;
            flex-direction: column;
            justify-content: space-around;
            flex: 1 1 auto;
        }

        .book {
            position: relative;
            background-color: grey;
            width: 40%;
            height: 7em;
            margin-left: 5%;
            padding-top: 5px;
            padding-left: 10px;
            font-family: Arial, sans-serif;
        }

        .book h3, h6 {
            margin-top: 3px;
        }

        .book input {
            position: absolute;
            bottom: 10px;
            right: 10px;
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

        .form-inline {
            margin-top: auto;
            margin-bottom: auto;
        }

        button#finder {
            margin-left: 5px;
            font-weight: bold;
            border-width: 2px;
        }
    </style>
</head>
<body class="d-flex flex-column h-100">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="#">Меню бронирования книг</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarColor03">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">К авторизации <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/usermenu">В меню</a>
                </li>
            </ul>
            <form class="form-inline">
                <select name="filter" id="filter" class="form-control">
                    <option name="filter" value="Повесть">Повесть</option>
                    <option name="filter" value="Роман">Роман</option>
                    <option name="filter" value="Поэма">Поэма</option>
                    <option name="filter" value="Приключения">Приключения</option>
                    <option name="filter" value="Комедия">Комедия</option>
                    <option name="filter" value="Трагедия">Трагедия</option>
                    <option name="filter" value="Драма">Драма</option>
                    <option name="filter" value="Ужасы">Ужасы</option>
                    <option name="filter" value="Мифы">Мифы</option>
                    <option name="filter" value="Рассказ">Рассказ</option>
                    <option name="filter" value="Сказка">Сказка</option>
                    <option name="filter" value="Баллада">Баллада</option>
                </select>
                <button class="btn btn-outline-success my-2 my-sm-0" id="finder" type="button">Поиск</button>
            </form>
        </div>
    </nav>
    <jsp:useBean id="books" scope="request" type="java.util.List"/>
    <jsp:useBean id="authors" scope="request" type="java.util.List"/>
    <div class="books">
        <c:forEach var="book" items="${books}">
            <form name="choosebook" action="${pageContext.request.contextPath}/orderbook" method="get">
                <div class="book">
                    <h3>${book.name}</h3>
                    <h6>Жанр: ${book.genre}</h6>
                    <input type="submit" class="btn btn-primary btn-sm" id="submit" value="Забронировать" />
                    <input type="hidden" name="id" value="${book.bookId}" />
                </div>
            </form>
        </c:forEach>
    </div>
    <footer class="footer mt-auto py-3">
        <div class="flex-footer">
            <span>Минск, 2020</span>
        </div>
    </footer>
</body>
</html>
