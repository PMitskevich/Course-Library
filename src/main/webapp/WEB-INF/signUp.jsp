<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 08.11.2020
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="theme-color" content="#563d7c">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Регистрация</title>
    <script>
        let users = <%= request.getAttribute("users") %>;
        function checkForm(form) {
            if (validateNonEmpty(form["surname"], document.getElementById('surname_help')) &&
                validateNonEmpty(form["name"], document.getElementById('name_help')) &&
                validateNonEmpty(form["patronymic"], document.getElementById('patronymic_help')) &&
                validatePhone(form["phone"], document.getElementById('phone_help')) &&
                validateNonEmpty(form["birthday"], document.getElementById('birthday_help')) &&
                validatePasswordRepeat(form["password"], form["passwordRepeat"], document.getElementById('passwordRepeat_help'))) {

                document.getElementById('surname_help').innerHTML = "";
                document.getElementById('name_help').innerHTML = "";
                document.getElementById('patronymic_help').innerHTML = "";
                document.getElementById('phone_help').innerHTML = "";
                document.getElementById('birthday_help').innerHTML = "";
                document.getElementById('phone_help').innerHTML = "";
                document.getElementById('password_help').innerHTML = "";
                document.getElementById('passwordRepeat_help').innerHTML = "";

                for (let user of users) {
                    if (user.login === form["login"].value) {
                        let errMessage = document.getElementById("error");
                        errMessage.style.visibility = "visible";
                        return;
                    }
                }
                form.submit();
            }
        }

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

        function validatePhone(inputField, helpText) {
            //    var regex = /^\d(\s)?\d{3}(\s)?\d{3}(\s)?\d{2}(\s)?\d{2}$/;
            let regex = /^(\+)(\s*)?([-_():=+]?\d[-_():=+]?){10,14}(\s*)?$/;

            if (!validateNonEmpty(inputField, helpText))
                return false;

            return validateRegExp(regex, inputField.value, helpText, "Введите корректный номер");
        }

        function validatePasswordRepeat(passwordField, passwordRepeatField, helpText) {
            let password = passwordField.value;
            let passwordRepeat = passwordRepeatField.value;

            if (!validateNonEmpty(passwordRepeatField, helpText))
                return false;

            if (password !== passwordRepeat) {
                helpText.innerHTML = "Пароль не совпадает";
                return false;
            }
            return true;
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

        function fillUsers() {
            // // let search = encodeURI(document.getElementById("search").value);
            // $.ajax("/library/findbooks", {
            //     type: "POST",
            //     contentType: "text/html; charset=UTF-8",
            //     headers: { "search": search },
            //     success: (result) => {
            //         users = result;
            //     }
            // })
        }
    </script>

    <style>
        span.help, h2 {
            color: #660000;
            font-style: italic;
        }

        h2 {
            visibility: hidden;
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
    </style>
</head>
<body class="d-flex flex-column h-100">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="#">Регистрация пользователя</a>
        <div class="collapse-navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/">К авторизации <span class="sr-only"></span></a>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container">
        <form name="signupmenu" action="${pageContext.request.contextPath}/signup" method="post">
            <div class="form-group">
                <label for="surname">Фамилия: </label>
                <input type="text" name="surname" value="" class="form-control" id="surname" placeholder="Фамилия"
                       onblur="validateNonEmpty(this, document.getElementById('surname_help'))" />
                <span class="help" id="surname_help"></span>
            </div>
            <div class="form-group">
                <label for="name">Имя: </label>
                <input type="text" class="form-control" id="name" placeholder="Имя" name="name" value=""
                       onblur="validateNonEmpty(this, document.getElementById('name_help'))" />
                <span class="help" id="name_help"></span>
            </div>
            <div class="form-group">
                <label for="name">Отчество: </label>
                <input type="text" class="form-control" id="patronymic" placeholder="Отчество" name="patronymic" value=""
                       onblur="validateNonEmpty(this, document.getElementById('patronymic_help'))" />
                <span class="help" id="patronymic_help"></span>
            </div>
            <div class="form-group">
                <label for="phone">Телефон: </label>
                <input type="text" class="form-control" id="phone" placeholder="Отчество" name="phone" value=""
                       onblur="validatePhone(this, document.getElementById('phone_help'))" />
                <span class="help" id="phone_help"></span>
            </div>
            <div class="form-group">
                <label for="birthday">Дата рождения: </label>
                <input type="date" class="form-control" id="birthday" placeholder="Дата рождения" name="birthday" value=""
                       onblur="validateNonEmpty(this, document.getElementById('birthday_help'))" />
                <span class="help" id="birthday_help"></span>
            </div>
            <div class="form-group">
                <label for="login">Логин: </label>
                <input type="text" class="form-control" id="login" placeholder="Логин" name="login" value=""
                       onblur="validateNonEmpty(this, document.getElementById('login_help'))" />
                <span class="help" id="login_help"></span>
            </div>
            <div class="form-group">
                <label for="password">Пароль: </label>
                <input type="password" class="form-control" placeholder="Пароль" id="password" name="password" value=""
                       onblur="validateNonEmpty(this, document.getElementById('password_help'))" />
                <span class="help" id="password_help"></span>
            </div>
            <div class="form-group">
                <label for="passwordRepeat">Повтор пароля: </label>
                <input type="password" class="form-control" placeholder="Повтор пароля" id="passwordRepeat" name="passwordRepeat" value=""
                       onblur="validatePasswordRepeat(document.getElementById('password'), this, document.getElementById('passwordRepeat_help'))" />
                <span class="help" id="passwordRepeat_help"></span>
            </div>

            <button type="button" class="btn btn-primary" onclick="checkForm(this.form)">Зарегистрироваться</button>
        </form>
        <h2 id="error">Такой пользователь уже существует</h2>
    </div>
    <footer class="footer mt-auto py-3">
        <div class="flex-footer">
            <span>Минск, 2020</span>
        </div>
    </footer>
</body>
</html>