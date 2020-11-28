package servlets;

import dao.AuthorDao;
import dao.BookDao;
import dao.PublishingDao;
import model.Author;
import model.Book;
import model.Publishing;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/deletebook")
public class DeleteBookServlet extends HttpServlet {
    private BookDao bookDao;
    private AuthorDao authorDao;
    private PublishingDao publishingDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bookDao = new BookDao();
        authorDao = new AuthorDao();
        publishingDao = new PublishingDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookDao.getAll();
        List<Author> authors = authorDao.getAll();
        List<Publishing> publishings = publishingDao.getAll();
        req.setAttribute("authors", authors);
        req.setAttribute("books", books);
        req.setAttribute("publishings", publishings);
        req.getRequestDispatcher("/WEB-INF/deleteBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long bookId = Long.parseLong(req.getParameter("bookId"));
        bookDao.delete(bookId);
        req.getRequestDispatcher("/WEB-INF/adminMenu.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
