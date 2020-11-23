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
    <title>Выбор книг</title>
    <style>
        .book {
            background-color: grey;
            width: 40%;
            margin-left: 5%;
            margin-bottom: 10px;
            padding-top: 5px;
            padding-left: 10px;
            font-family: Arial, sans-serif;
        }

        .book h3, h6 {
            margin-top: 3px;
        }

        .book input {
            margin-left: 80%;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <h1>Меню бронирования книг</h1>
    <jsp:useBean id="books" scope="request" type="java.util.List"/>
    <jsp:useBean id="authors" scope="request" type="java.util.List"/>
    <c:forEach var="book" items="${books}">
        <form name="choosebook" action="${pageContext.request.contextPath}/orderbook" method="get">
            <div class="book">
                <h3>${book.name}</h3>
                <h6>Жанр: ${book.genre}</h6>
                <input type="submit" id="submit" value="Забронировать" />
                <input type="hidden" name="id" value="${book.bookId}" />
            </div>
        </form>
    </c:forEach>
</body>
</html>
