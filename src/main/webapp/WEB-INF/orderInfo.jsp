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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="theme-color" content="#563d7c">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Информация</title>
    <style>
        .form {
            width: 30%;
            border: 2px solid #1E90FF;
            margin: 20px;
            padding: 20px;
        }

        footer {
            background-color: #C0C0C0;
        }

        span.help, h2 {
            color: #660000;
            font-style: italic;
        }

        .flex-footer {
            display: flex;
            justify-content: center;
            align-items: center;
            color: antiquewhite;
        }

        .field {
            margin-bottom: 5px;
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

        function validateQuantity(inputField, helpText, quantity) {
            if (!validateNonEmpty(inputField, helpText))
                return false;

            if (inputField.value > quantity) {
                if (helpText != null)
                    helpText.innerHTML = "Введите корректное значение";
                return false;
            } else {
                if (helpText != null)
                    helpText.innerHTML = "";
                return true;
            }
        }

        function checkForm(form) {
            let quantityInput = document.getElementById("quantity");
            let quantityHelp = document.getElementById("quantity_help");
            let quantity = ${book.quantity};
            if (validateQuantity(quantityInput, quantityHelp, quantity)) {
                form.submit();
            }
        }
    </script>
</head>
<body class="d-flex flex-column h-100">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="#">Информация о книге</a>
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
    <div class="form">
        <form name="bookinfo" action="${pageContext.request.contextPath}/addlending" method="post">
            <input type="hidden" value="${book.bookId}" />

            <div class="field">
                <label for="bookname">Название</label>
                <input type="text" id="bookname" name="bookname" class="form-control" value="${book.name}" />
            </div>
            <div class="field">
                <label for="author">Автор</label>
                <input type="text" id="author" name="author" class="form-control" value="${author.fullName}" />
            </div>
            <div class="field">
                <label for="genre">Жанр</label>
                <input type="text" id="genre" name="genre" class="form-control" value="${book.genre}" />
            </div>
            <div class="field">
                <label for="publishing">Издательство</label>
                <input type="text" id="publishing" name="publishing" class="form-control" value="${publishing.publishingName}" />
            </div>
            <div class="field">
                <label for="quantity">Желаемое количество(Доступно ${book.quantity})</label>
                <input type="text" id="quantity" name="quantity" class="form-control" value=""
                       onblur="validateQuantity(this, document.getElementById('quantity_help'), ${book.quantity})" />
                <span class="help" id="quantity_help"></span>
            </div>
            <div class="field">
                <input type="button" class="btn btn-primary btn-sm" value="Заказать" onclick="checkForm(this.form)" />
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
