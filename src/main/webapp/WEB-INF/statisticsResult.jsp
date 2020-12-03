<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 02.12.2020
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
    <title>Результаты поиска</title>
    <script>
        let totalBookAmount = 0;

        function addBooksAmount(booksAmount) {
            totalBookAmount += booksAmount;
        }

        function setPercents() {
            let rows = document.getElementsByTagName("tr");
            for (let i = 1; rows.length; i++) {
                let trContentElements = rows[i].childNodes;
                trContentElements[7].textContent = ((parseInt(trContentElements[5].textContent, 10) / totalBookAmount) * 100).toString();
                trContentElements[7].textContent += "%";
            }
        }

    </script>
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
        <a class="navbar-brand" href="#">Результаты поиска</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarColor03">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">К авторизации <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/adminmenu">В меню</a>
                </li>
            </ul>
        </div>
    </nav>

    <jsp:useBean id="genresmap" scope="request" type="java.util.Map"/>
    <c:choose>
        <c:when test="${genresmap.size() > 0}">
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Жанр</th>
                    <th scope="col">Количество книг</th>
                    <th scope="col">Процентное соотношение</th>
                </tr>
                </thead>
                <tbody>
                    <c:set var="counter" value="1" />
                    <c:forEach var="element" items="${genresmap}">
                        <tr>
                            <td>${counter}</td>
                            <td>${element.key}</td>
                            <td>${element.value}</td>
                            <td></td>
                        </tr>
                        <script>
                            addBooksAmount(${element.value});
                        </script>
                        <c:set var="counter" value="${counter + 1}" />
                    </c:forEach>
                </tbody>
                <script>
                    setPercents();
                </script>
            </table>
        </c:when>
        <c:otherwise>
            <div class="empty-lendings">
                <h1>Заказы отсутствуют</h1>
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
