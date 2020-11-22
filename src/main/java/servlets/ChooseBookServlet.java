package servlets;

import dao.AuthorDao;
import dao.BookDao;
import model.Author;
import model.Book;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/choosebook")
public class ChooseBookServlet extends HttpServlet {
    private BookDao bookDao;
    private AuthorDao authorDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bookDao = new BookDao();
        authorDao = new AuthorDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookDao.getAll();
        List<Author> authors = authorDao.getAll();
        req.setAttribute("books", books);
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("/WEB-INF/orderBook.jsp").forward(req, resp);
    }

    @Override
    public void destroy() { super.destroy(); }
}
