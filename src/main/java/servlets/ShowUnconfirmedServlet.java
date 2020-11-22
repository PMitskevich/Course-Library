package servlets;

import dao.AuthorDao;
import dao.BookDao;
import dao.LendingDao;
import dao.LibrarianDao;
import model.Author;
import model.Book;
import model.Lending;
import model.Librarian;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/showunconfirmed")
public class ShowUnconfirmedServlet extends HttpServlet {
    private LendingDao lendingDao;
    private AuthorDao authorDao;
    private BookDao bookDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        lendingDao = new LendingDao();
        authorDao = new AuthorDao();
        bookDao = new BookDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        long visitorId = (long) session.getAttribute("id_visitor");
        List<Lending> lendings = lendingDao.getUnconfirmed(visitorId);
        List<Author> authors = authorDao.getAll();
        List<Book> books = bookDao.getAll();

        req.setAttribute("unconfirmedLendings", lendings);
        req.setAttribute("authors", authors);
        req.setAttribute("books", books);
        req.getRequestDispatcher("/WEB-INF/unconfirmedLendings.jsp").forward(req, resp);
    }

    @Override
    public void destroy() { super.destroy(); }
}
