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
    <title>Запросы на выдачу</title>
</head>
<body>
    <h1>Имеющиеся запросы</h1>
    <jsp:useBean id="lendings" scope="request" type="java.util.List"/>
    <jsp:useBean id="books" scope="request" type="java.util.List"/>
    <jsp:useBean id="authors" scope="request" type="java.util.List"/>
    <jsp:useBean id="visitors" scope="request" type="java.util.List"/>

    <c:choose>
        <c:when test="${lendings.size() > 0}">
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

                <form name="orderinfo" action="${pageContext.request.contextPath}/orderinfo" method="post">
                    <input type="hidden" name="lendingid" value="${lending.lendingId}" />
                    <input type="hidden" name="visitorid" value="${visitor.visitorId}" />
                    Название книги: <input style="margin-bottom: 5px;" type="text" name="bookname" value="${book.name}" /><br />
                    Автор: <input style="margin-bottom: 5px;" type="text" name="author" value="${author.name}" /><br />
                    Жанр: <input style="margin-bottom: 5px;" type="text" name="genre" value="${book.genre}" /><br />
                    Имя посетителя: <input style="margin-bottom: 5px;" type="text" name="visitorname" value="${visitor.fullName}" /><br />
                    Дата заказа: <input style="margin-bottom: 5px;" type="text" name="lenddate" value="${lending.lendDate}" /><br />
                    Дата возврата: <input style="margin-bottom: 5px;" type="text" name="returndate" value="" /><br />
                    Заказанное количество: <input style="margin-bottom: 5px;" type="text" name="lendquantity" value="${lending.lendQuantity}"><br />
                    <input style="margin-bottom: 10px;" type="submit" value="Подтвердить" /><br />
                    <h2>---------------------------------------------------------</h2>
                </form>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <h1>Подтверждённые заказы отсутствуют</h1>
        </c:otherwise>
    </c:choose>
</body>
</html>
