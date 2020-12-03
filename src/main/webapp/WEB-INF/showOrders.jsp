<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 07.11.2020
  Time: 21:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="theme-color" content="#563d7c">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Запросы на выдачу</title>
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

        .form {
            width: 30%;
            border: 2px solid #1E90FF;
            margin: 20px;
            padding: 20px;
        }

        .field {
            margin-bottom: 5px;
        }

        span.help, h2 {
            color: #660000;
            font-style: italic;
        }
    </style>
    <script>
        function validateNonEmpty(inputField, helpText) {
            if (inputField.value.length === 0) {
                if (helpText != null)
                    helpText.innerHTML = "Заполните поле";
                return false;
            } else {
                if (helpText != null)
                    helpText.innerHTML = "";
                return true;
            }
        }

        function validateDate(inputField, helpText) {
            let reqex = /^\d{4}-\d{2}-\d{2}$/;
            if (!validateNonEmpty(inputField, helpText)) {
                return false;
            }

            return validateRegExp(reqex, inputField.value, helpText, "Введите корректную дату");
        }

        function validateRegExp(regex, inputStr, helpText, helpMessage) {
            if (!regex.test(inputStr)) {
                if (helpText != null)
                    helpText.innerHTML = helpMessage;
                return false;
            } else {
                if (helpText != null)
                    helpText.innerHTML = "";
                return true;
            }
        }

        function checkForm(form) {
            let returnDateField = document.getElementById("returndate");
            let returnDateHelp = document.getElementById("returndate_help");
            if (validateDate(returnDateField, returnDateHelp)) {
                document.getElementById("returndate_help").innerHTML = "";
                form.submit();
            }
        }
    </script>
</head>
<body class="d-flex flex-column h-100">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="#">Запросы на выдачу книг</a>
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
    <jsp:useBean id="lendings" scope="request" type="java.util.List"/>
    <jsp:useBean id="books" scope="request" type="java.util.List"/>
    <jsp:useBean id="authors" scope="request" type="java.util.List"/>
    <jsp:useBean id="visitors" scope="request" type="java.util.List"/>

    <c:choose>
        <c:when test="${lendings.size() > 0}">
            <div class="forms">
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

                    <c:forEach begin="0" end="${visitors.size() - 1}" var="visitorsCount">
                        <c:if test="${lending.visitorId == visitors.get(visitorsCount).visitorId}">
                            <c:set var="visitor" value="${visitors.get(visitorsCount)}" />
                            <c:set var="visitorsCount" value="${visitors.size()}" scope="page" />
                        </c:if>

                        <c:set var="visitorsCount" value="${visitorsCount + 1}" scope="page" />
                    </c:forEach>

                    <div class="form">
                        <form name="orderinfo" action="${pageContext.request.contextPath}/orderinfo" method="post">
                            <input type="hidden" name="lendingid" value="${lending.lendingId}" />
                            <input type="hidden" name="visitorid" value="${visitor.visitorId}" />
                            <div class="field">
                                <label for="title">Название книги</label>
                                <input type="text" id="title" name="bookname" value="${book.name}" />
                            </div>
                            <div class="field">
                                <label for="author">Автор</label>
                                <input type="text" id="author" name="author" value="${author.fullName}" />
                            </div>
                            <div class="field">
                                <label for="genre">Жанр</label>
                                <input type="text" id="genre" name="genre" value="${book.genre}" />
                            </div>
                            <div class="field">
                                <label for="visitorname">Имя посетителя</label>
                                <input type="text" id="visitorname" name="visitorname" value="${visitor.fullName}" />
                            </div>
                            <div class="field">
                                <label for="lenddate">Дата заказа</label>
                                <input type="text" id="lenddate" name="lenddate" value="${lending.lendDate}" />
                            </div>
                            <div class="field">
                                <label for="returndate">Дата возврата</label>
                                <input type="text" id="returndate" name="returndate" value=""
                                       onblur="validateDate(this, document.getElementById('returndate_help'))" />
                                <span class="help" id="returndate_help"></span>
                            </div>
                            <div class="field">
                                <label for="lendquantity">Заказанное количество</label>
                                <input type="text" id="lendquantity" name="lendquantity" value="${lending.lendQuantity}">
                            </div>
                            <div>
                                <input type="button" class="btn btn-primary btn-sm" value="Подтвердить"
                                       onclick="checkForm(this.form)" />
                            </div>
                        </form>
                    </div>
                </c:forEach>
            </div>
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
