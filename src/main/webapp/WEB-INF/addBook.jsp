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
    <title>Заказ новой книги</title>
    <script type="text/javascript">
        function chooseAuthor() {
            let selectBox = document.getElementById("selectAuthor");
            let selectedAuthor = selectBox.options[selectBox.selectedIndex].value;

            if (selectedAuthor === "Ввести нового автора") {
                document.getElementById("divauthor").remove();

                let inputElement = document.createElement("input");
                inputElement.setAttribute("type", "text");
                inputElement.setAttribute("name", "author");
                inputElement.setAttribute("placeholder", "ФИО")

                let divElement = document.createElement("div");
                divElement.className = "field";
                divElement.appendChild(document.createTextNode("Автор: "));
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
                inputElement.setAttribute("name", "publishing");

                let divElement = document.createElement("div");
                divElement.className = "field";
                divElement.appendChild(document.createTextNode("Издательство: "));
                divElement.appendChild(inputElement);
                divElement.appendChild(document.createElement("br"));
                document.getElementById("genre").insertAdjacentElement("afterend", divElement);
            }
        }
    </script>
    <style>
        .field {
            margin-bottom: 5px;
        }
    </style>

</head>
<body>
<form name="newbookorder" action="${pageContext.request.contextPath}/addbook" method="post">
    <div class="field">
        Название: <input type="text" name="title" value="" /><br />
    </div>

    <div class="field" id="divauthor">
        <label for="selectAuthor">Автор: </label>
        <select id="selectAuthor" name="author" onchange="chooseAuthor();">
            <jsp:useBean id="authors" scope="request" type="java.util.List"/>
            <c:forEach var="author" items="${authors}">
                <option name="author" value="${author.fullName}">${author.fullName}</option>
            </c:forEach>
            <option value="Ввести нового автора">Ввести нового автора</option>
        </select><br />
    </div>

    <div class="field" id="genre">
        <label for="selectGenre">Жанр: </label>
        <select id="selectGenre" name="genre">
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
        <select id="selectPublishing" name="publishing" onchange="choosePublishing();">
            <jsp:useBean id="publishings" scope="request" type="java.util.List"/>
            <c:forEach var="publishing" items="${publishings}">
                <option name="publishing" value="${publishing.publishingName}">${publishing.publishingName}</option>
            </c:forEach>
            <option value="Ввести новое издательство">Ввести новое издательство</option>
        </select><br />
    </div>

    <div class="field">
        Количество: <input type="text" name="quantity" value="" /><br />
    </div>
    <input type="submit" value="Добавить">
</form>
</body>
</html>
