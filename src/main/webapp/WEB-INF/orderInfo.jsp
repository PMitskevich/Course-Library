<jsp:useBean id="publishing" scope="request" type="model.Publishing"/>
<jsp:useBean id="author" scope="request" type="model.Author"/>
<jsp:useBean id="book" scope="request" type="model.Book"/>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 05.11.2020
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Информация</title>
</head>
<body>
    <h1>Информация о книге</h1>
    <form name="bookinfo" action="${pageContext.request.contextPath}/addlending" method="post">
        <input type="hidden" value="${book.bookId}" />
        Название: <input style="margin-bottom: 5px;" type="text" name="bookname" value="${book.name}" /><br />
        Автор: <input style="margin-bottom: 5px;" type="text" name="author" value="${author.fullName}" /><br />
        Жанр: <input style="margin-bottom: 5px;" type="text" name="genre" value="${book.genre}" /><br />
        Издательство: <input style="margin-bottom: 5px;" type="text" name="publishing" value="${publishing.publishingName}" /><br />
        Желаемое количество(Доступно ${book.quantity}): <input style="margin-bottom: 5px;" type="text" name="quantity" value="" /><br />
        <input type="submit" value="Заказать" />
    </form>
</body>
</html>
