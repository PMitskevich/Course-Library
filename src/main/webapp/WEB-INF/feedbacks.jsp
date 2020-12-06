<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 06.12.2020
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="theme-color" content="#563d7c">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Отзывы</title>
    <style>
        .admin-feedback {
            color: green;
        }

        .field {
            margin-bottom: 5px;
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

        .container {
            margin-top: 15px;
        }

    </style>
</head>
<body class="d-flex flex-column h-100">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="#">Отзывы</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarColor03">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">К авторизации <span class="sr-only">(current)</span></a>
                </li>
                <c:choose>
                    <c:when test="${sessionScope.id_visitor == -1 }">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/adminmenu">В меню</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/usermenu">В меню</a>
                        </li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </nav>

    <jsp:useBean id="feedbacks" scope="request" type="java.util.List"/>
    <jsp:useBean id="users" scope="request" type="java.util.List"/>

    <div class="container">
        <div class="feedbacks">
            <c:forEach var="feedback" items="${feedbacks}">
                <c:forEach begin="0" end="${users.size() - 1}" var="usersCount">

                    <c:if test="${feedback.userId == users.get(usersCount).userId}">
                        <c:set var="user" value="${users.get(usersCount)}" />
                        <c:set var="usersCount" value="${users.size()}" />
                    </c:if>

                    <c:set var="usersCount" value="${usersCount + 1}" scope="page" />
                </c:forEach>

                    <c:choose>
                        <c:when test="${user.isAdmin == true}">
                            <h3 class="admin-feedback">${user.login}</h3>
                        </c:when>
                        <c:otherwise>
                            <h3>${user.login}</h3>
                        </c:otherwise>
                    </c:choose>
                    <textarea cols="60" rows="5">${feedback.feedback}</textarea>
            </c:forEach>
        </div>
    </div>

    <div class="container form-container">
        <form name="placefeedback" action="${pageContext.request.contextPath}/showfeedback" method="post">
            <div class="field">
                <textarea name="feedbackText" id="feedback" cols="60" rows="5"></textarea>
            </div>
            <div class="field">
                <input type="submit" class="btn btn-primary btn-sm" value="Добавить" /><br />
            </div>
        </form>
    </div>
    <footer class="footer mt-auto py-3">
        <div class="flex-footer">
            <span>Минск, 2020</span>
        </div>
    </footer>
</body>
</html>
