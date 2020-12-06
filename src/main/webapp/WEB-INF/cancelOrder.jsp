<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 03.12.2020
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="theme-color" content="#563d7c">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Отмена брони</title>
    <style>
        footer {
            background-color: #C0C0C0;
        }

        .flex-footer {
            display: flex;
            justify-content: center;
            align-items: center;
            color: antiquewhite;
        }

        .empty-lendings {
            display: flex;
            justify-content: center;
            align-items: center;
            flex: 1 1 auto;
        }
    </style>
</head>
<body class="d-flex flex-column h-100">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="#">Список запросов на бронь</a>
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
        </div>
    </nav>
    <jsp:useBean id="lendings" scope="request" type="java.util.List"/>
    <jsp:useBean id="books" scope="request" type="java.util.List"/>
    <jsp:useBean id="authors" scope="request" type="java.util.List"/>
    <jsp:useBean id="librarian" scope="request" type="model.Librarian"/>

    <c:choose>
        <c:when test="${lendings.size() > 0}">
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th scope="col"># заказа</th>
                    <th scope="col">Название книги</th>
                    <th scope="col">Автор</th>
                    <th scope="col">Жанр</th>
                    <th scope="col">Дата заказа</th>
                    <th scope="col">Дата возврата</th>
                    <th scope="col">Заказанное количество</th>
                    <th scope="col">Имя библиотекаря</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="lending" items="${lendings}">
                    <c:forEach begin="0" end="${books.size() - 1}" var="booksCount">
                        <c:if test="${lending.bookId == books.get(booksCount).bookId}">
                            <c:set var="book" value="${books.get(booksCount)}" />

                            <c:forEach begin="0" end="${authors.size() - 1}" var="authorsCount">
                                <c:if test="${book.authorId == authors.get(authorsCount).authorId}">
                                    <c:set var="author" value="${authors.get(authorsCount)}" />
                                    <c:set var="authorsCount" value="${authors.size()}" scope="page" />
                                </c:if>

                                <c:set var="authorsCount" value="${authorsCount + 1}" scope="page" />
                            </c:forEach>

                            <c:set var="booksCount" value="${books.size()}" scope="page" />
                        </c:if>
                        <c:set var="booksCount" value="${booksCount + 1}" scope="page" />
                    </c:forEach>

                    <div class="row">
                        <tr>
                            <th scope="row">${lending.lendingId}</th>
                            <td>${book.name}</td>
                            <td>${author.fullName}</td>
                            <td>${book.genre}</td>
                            <td>${lending.lendDate}</td>
                            <c:choose>
                                <c:when test="${lending.returnDate == '0000-01-01'}">
                                    <td>------------</td>
                                    <td>${lending.lendQuantity}</td>
                                    <td>------------</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${lending.returnDate}</td>
                                    <td>${lending.lendQuantity}</td>
                                    <td>${librarian.fullName}</td>
                                </c:otherwise>
                            </c:choose>
                            <td>
                                <form action="${pageContext.request.contextPath}/cancelorder" method="post">
                                    <input type="hidden" name="lendingId" value="${lending.lendingId}" />
                                    <button class="btn btn-outline-danger my-2 my-sm-0" id="cancel" type="submit">Удалить</button>
                                </form>
                            </td>
                        </tr>
                    </div>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="empty-lendings">
                <h1>Подтверждённые заказы отсутствуют</h1>
            </div>
        </c:otherwise>
    </c:choose>
    <footer class="footer mt-auto py-3">
        <div class="flex-footer">
            <span>Минск, 2020</span>
        </div>
    </footer>
</body>
</html>
