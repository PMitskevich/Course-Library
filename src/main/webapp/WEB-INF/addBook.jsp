<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 22.11.2020
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="theme-color" content="#563d7c">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Заказ новой книги</title>
    <script type="text/javascript">
        function chooseAuthor() {
            let selectBox = document.getElementById("selectAuthor");
            let selectedAuthor = selectBox.options[selectBox.selectedIndex].value;

            if (selectedAuthor === "Ввести нового автора") {
                document.getElementById("divauthor").remove();

                let inputElement = document.createElement("input");
                inputElement.setAttribute("type", "text");
                inputElement.setAttribute("id", "author");
                inputElement.setAttribute("name", "author");
                inputElement.setAttribute("class", "form-control");
                inputElement.setAttribute("placeholder", "ФИО");

                let divElement = document.createElement("div");
                // divElement.className = "form-group";
                divElement.className = "field"
                let label = document.createElement("label");
                label.setAttribute("for", "author");
                label.innerText = "Автор";
                divElement.appendChild(label);
                divElement.appendChild(inputElement);
                divElement.appendChild(document.createElement("br"));
                document.getElementById("genre").insertAdjacentElement("beforebegin", divElement);
            }
        }

        function choosePublishing() {
            let selectBox = document.getElementById("selectPublishing");
            let selectedPublishing = selectBox.options[selectBox.selectedIndex].value;

            if (selectedPublishing === "Ввести новое издательство") {
                document.getElementById("divpublishing").remove();

                let inputElement = document.createElement("input");
                inputElement.setAttribute("type", "text");
                inputElement.setAttribute("id", "publishing");
                inputElement.setAttribute("name", "publishing");
                inputElement.setAttribute("class", "form-control");
                inputElement.setAttribute("placeholder", "Издательство");

                let divElement = document.createElement("div");
                // divElement.className = "form-group";
                divElement.className = "field";
                let label = document.createElement("label");
                label.setAttribute("for", "publishing");
                label.innerText = "Издательство";
                divElement.appendChild(label);
                divElement.appendChild(inputElement);
                divElement.appendChild(document.createElement("br"));
                document.getElementById("genre").insertAdjacentElement("afterend", divElement);
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

        .form {
            width: 30%;
            border: 2px solid #1E90FF;
            margin: 20px;
            padding: 20px;
        }

        .field {
            margin-bottom: 5px;
        }
    </style>

</head>
<body class="d-flex flex-column h-100">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="#">Меню заказа новой книги</a>
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
    <div class="form">
        <form name="newbookorder" action="${pageContext.request.contextPath}/addbook" method="post">
            <div class="field">
                <label for="title">Название</label>
                <input type="text" id="title" name="title" value="" class="form-control" placeholder="Название" /><br />
            </div>

            <div class="field" id="divauthor">
                <label for="selectAuthor">Автор: </label>
                <select id="selectAuthor" name="author" class="form-control" onchange="chooseAuthor();">
                    <jsp:useBean id="authors" scope="request" type="java.util.List"/>
                    <c:forEach var="author" items="${authors}">
                        <option name="author" value="${author.fullName}">${author.fullName}</option>
                    </c:forEach>
                    <option value="Ввести нового автора">Ввести нового автора</option>
                </select><br />
            </div>

            <div class="field" id="genre">
                <label for="selectGenre">Жанр</label>
                <select id="selectGenre" name="genre" class="form-control">
                    <option name="genre" value="Повесть">Повесть</option>
                    <option name="genre" value="Роман">Роман</option>
                    <option name="genre" value="Поэма">Поэма</option>
                    <option name="genre" value="Приключения">Приключения</option>
                    <option name="genre" value="Комедия">Комедия</option>
                    <option name="genre" value="Трагедия">Трагедия</option>
                    <option name="genre" value="Драма">Драма</option>
                    <option name="genre" value="Ужасы">Ужасы</option>
                    <option name="genre" value="Мифы">Мифы</option>
                    <option name="genre" value="Рассказ">Рассказ</option>
                    <option name="genre" value="Сказка">Сказка</option>
                    <option name="genre" value="Баллада">Баллада</option>
                </select>
            </div>

            <div class="field" id="divpublishing">
                <label for="selectPublishing">Издательство: </label>
                <select id="selectPublishing" name="publishing" class="form-control" onchange="choosePublishing();">
                    <jsp:useBean id="publishings" scope="request" type="java.util.List"/>
                    <c:forEach var="publishing" items="${publishings}">
                        <option name="publishing" value="${publishing.publishingName}">${publishing.publishingName}</option>
                    </c:forEach>
                    <option value="Ввести новое издательство">Ввести новое издательство</option>
                </select><br />
            </div>

            <div class="field">
                <label for="quantity">Количество</label>
                <input type="text" id="quantity" name="quantity" class="form-control" placeholder="Количество" value="" /><br />
            </div>
            <button type="submit" class="btn btn-primary btn-sm">Добавить</button>
        </form>
    </div>
    <footer class="footer mt-auto py-3">
        <div class="flex-footer">
            <span>Минск, 2020</span>
        </div>
    </footer>
</body>
</html>
