<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 28.11.2020
  Time: 23:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="theme-color" content="#563d7c">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <title>Удаление записи о книге</title>
    <style>
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

        .form-inline {
            margin-top: auto;
            margin-bottom: auto;
        }

        .form-inline #finder {
            border-color: green;
            color: green;
            margin-right: 5px;
        }

        .form-inline #finder:hover {
            color: white;
        }

        .form-inline #cancel:hover {
            color: white;
        }

        .form-inline #cancel {
            border-color: darkred;
            color: darkred;
        }

        .form {
            width: 50%;
            border: 2px solid #1E90FF;
            margin: 20px;
            padding: 20px;
        }
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <script>
        let readyBooks = [];

        class ReadyBook {
            constructor(form) {
                this.bookId = form["bookId"].value;
                this.authorId = form["authorId"].value;
                this.publishingId = form["publishingId"].value;
                this.title = form["title"].value;
                this.author = form["author"].value;
                this.genre = form["genre"].value;
                this.publishing = form["publishing"].value;
                this.quantity = form["quantity"].value;
            }
        }

        function fillReadyBooksArray() {
            let forms = document.querySelectorAll(".form > form");
            for (let form of forms) {
                readyBooks.push(new ReadyBook(form));
            }
        }

        $(document).ready(function () {
            $("#finder").click(function () {
                let search = document.getElementById("search").value.toLowerCase();
                let searchResults = [];
                for (let readyBook of readyBooks) {
                    let title = readyBook.title.toLowerCase();
                    if (title.indexOf(search) !== -1) {
                        searchResults.push(readyBook);
                    }
                }
                if (searchResults.length !== 0) {
                    createSearchForms(searchResults);
                }
                else {
                    alert("Совпадений не найдено");
                }
            });

            $("#cancel").click(function () {
                createSearchForms(readyBooks);
                let search = document.getElementById("search");
                search.value = "";
            });
        });

        function createSearchForms(searchResults) {
            let forms = document.querySelector("div.forms");
            forms.innerHTML = "";
            searchResults.forEach(element => {
                let outerDiv = document.createElement("div");
                outerDiv.setAttribute("class", "form");

                let form = document.createElement("form");
                form.setAttribute("action", "${pageContext.request.contextPath}/deletebook");
                form.setAttribute("method", "post");
                let bookIdInput = document.createElement("input");
                bookIdInput.setAttribute("name", "bookId");
                bookIdInput.setAttribute("type", "hidden");
                bookIdInput.setAttribute("value", element.bookId);

                let divTitle = document.createElement("div");
                divTitle.setAttribute("class", "field");
                let titleInput = document.createElement("input");
                titleInput.setAttribute("name", "title");
                titleInput.setAttribute("type", "text");
                titleInput.setAttribute("value", element.title);
                divTitle.appendChild(document.createTextNode("Название книги: "));
                divTitle.appendChild(titleInput);
                divTitle.appendChild(document.createElement("br"));

                let divAuthor = document.createElement("div");
                divAuthor.setAttribute("class", "field");
                divAuthor.appendChild(document.createTextNode("Автор: "));
                let authorInput = document.createElement("input");
                authorInput.setAttribute("type", "text");
                authorInput.setAttribute("name", "author");
                authorInput.setAttribute("value", element.author);
                divAuthor.appendChild(authorInput);
                divAuthor.appendChild(document.createElement("br"));
                let authorIdInput = document.createElement("input");
                authorIdInput.setAttribute("type", "hidden");
                authorIdInput.setAttribute("name", "authorId");
                authorIdInput.setAttribute("value", element.authorId);

                let divGenre = document.createElement("div");
                divGenre.setAttribute("class", "field");
                divGenre.appendChild(document.createTextNode("Жанр: "));
                let genreInput = document.createElement("input");
                genreInput.setAttribute("type", "text");
                genreInput.setAttribute("name", "genre");
                genreInput.setAttribute("value", element.genre);
                divGenre.appendChild(genreInput);
                divGenre.appendChild(document.createElement("br"));

                let divPublishing = document.createElement("div");
                divPublishing.setAttribute("class", "field");
                divPublishing.appendChild(document.createTextNode("Издательство: "));
                let publishingInput = document.createElement("input");
                publishingInput.setAttribute("type", "text");
                publishingInput.setAttribute("name", "publishing");
                publishingInput.setAttribute("value", element.publishing);
                divPublishing.appendChild(publishingInput);
                divPublishing.appendChild(document.createElement("br"));
                let publishingIdInput = document.createElement("input");
                publishingIdInput.setAttribute("type", "hidden");
                publishingIdInput.setAttribute("name", "publishingId");
                publishingIdInput.setAttribute("value", element.publishingId);
                divPublishing.appendChild(publishingIdInput);

                let divQuantity = document.createElement("div");
                divQuantity.setAttribute("class", "field");
                divQuantity.appendChild(document.createTextNode("Количество: "));
                let quantityInput = document.createElement("input");
                quantityInput.setAttribute("type", "text");
                quantityInput.setAttribute("name", "quantity");
                quantityInput.setAttribute("value", element.quantity);
                divQuantity.appendChild(quantityInput);
                divQuantity.appendChild(document.createElement("br"));

                let divSubmit = document.createElement("div");
                divSubmit.setAttribute("class", "field");
                let submitInput = document.createElement("input");
                submitInput.setAttribute("type", "submit");
                submitInput.setAttribute("class", "btn btn-primary btn-sm");
                submitInput.setAttribute("value", "Удалить");
                divSubmit.appendChild(submitInput);
                divSubmit.appendChild(document.createElement("br"));

                form.appendChild(bookIdInput);
                form.appendChild(divTitle);
                form.appendChild(divAuthor);
                form.appendChild(divGenre);
                form.appendChild(divPublishing);
                form.appendChild(divQuantity);
                form.appendChild(divSubmit);
                outerDiv.appendChild(form);
                forms.appendChild(outerDiv);
            });
        }
    </script>
</head>
<body class="d-flex flex-column h-100">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="#">Меню редактирования</a>
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
            <form class="form-inline">
                <input class="form-control mr-sm-2" id="search" name="findbyname" type="search" placeholder="Поиск" aria-label="Search" />
                <button class="btn btn-outline-success my-2 my-sm-0" id="finder" type="button">Поиск</button>
                <button class="btn btn-outline-danger my-2 my-sm-0" id="cancel" type="button">Сброс</button>
            </form>
        </div>
    </nav>
    <jsp:useBean id="books" scope="request" type="java.util.List"/>
    <jsp:useBean id="authors" scope="request" type="java.util.List"/>
    <jsp:useBean id="publishings" scope="request" type="java.util.List"/>
    <c:choose>
        <c:when test="${books.size() > 0}">
            <div class="forms">
                <c:forEach var="book" items="${books}">
                    <c:forEach begin="0" end="${authors.size() - 1}" var="authorsCount">

                        <c:if test="${book.authorId == authors.get(authorsCount).authorId}">
                            <c:set var="author" value="${authors.get(authorsCount)}" />
                        </c:if>

                        <c:set var="authorsCount" value="${authorsCount + 1}" scope="page" />
                    </c:forEach>

                    <c:forEach begin="0" end="${publishings.size() - 1}" var="publishingsCount">

                        <c:if test="${book.publishingId == publishings.get(publishingsCount).publishingId}">
                            <c:set var="publishing" value="${publishings.get(publishingsCount)}" />
                            <c:set var="publishingsCount" value="${publishings.size()}" scope="page" />
                        </c:if>

                        <c:set var="publishingsCount" value="${publishingsCount + 1}" scope="page" />
                    </c:forEach>
                    <div class="form">
                        <form name="deletebookinfo" action="${pageContext.request.contextPath}/deletebook" method="post">
                            <input type="hidden" name="bookId" value="${book.bookId}">
                            <div class="field">
                                Название книги: <input type="text" name="title" value="${book.name}" /><br />
                            </div>

                            <div class="field">
                                Автор: <input type="text" name="author" value="${author.fullName}"><br />
                                <input type="hidden" name="authorId" value="${author.authorId}" />
                            </div>

                            <div class="field">
                                Жанр: <input type="text" name="genre" value="${book.genre}" /><br />
                            </div>

                            <div class="field">
                                Издательство:<input type="text" name="publishing" value="${publishing.publishingName}"><br />
                                <input type="hidden" name="publishingId" value="${publishing.publishingId}" />
                            </div>

                            <div class="field">
                                Количество: <input type="text" name="quantity" value="${book.quantity}" /><br />
                            </div>

                            <div class="field">
                                <input type="submit" value="Удалить" /><br />
                            </div>
                        </form>
                    </div>
                </c:forEach>
            </div>
            <script>
                fillReadyBooksArray();
            </script>
        </c:when>
        <c:otherwise>
            <h1>Книги в библиотеке отсутствуют</h1>
        </c:otherwise>
    </c:choose>
    <footer class="footer mt-auto py-3">
        <div class="flex-footer">
            <span>Минск, 2020</span>
        </div>
    </footer>
</body>
</html>
