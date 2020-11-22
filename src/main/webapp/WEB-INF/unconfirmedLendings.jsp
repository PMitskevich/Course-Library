<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 07.11.2020
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Неподтверждённые книги</title>
</head>
<body>
    <h1>Список неподтверждённых книг</h1>
    <jsp:useBean id="unconfirmedLendings" scope="request" type="java.util.List"/>
    <jsp:useBean id="books" scope="request" type="java.util.List"/>
    <jsp:useBean id="authors" scope="request" type="java.util.List"/>

    <c:choose>
        <c:when test="${unconfirmedLendings.size() > 0}">
            <table>
                <tr>
                    <th>Название книги</th>
                    <th>Автор</th>
                    <th>Жанр</th>
                    <th>Дата заказа</th>
                    <th>Заказанное количество</th>
                </tr>
                <c:forEach var="lending" items="${unconfirmedLendings}">
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

                    <tr>
                        <td>${book.name}</td>
                        <td>${author.fullName}</td>
                        <td>${book.genre}</td>
                        <td>${lending.lendDate}</td>
                        <td>${lending.lendQuantity}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <h1>Неподтверждённые заказы отсутствуют</h1>
        </c:otherwise>
    </c:choose>
</body>
</html>
