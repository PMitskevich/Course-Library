package servlets;

import dao.AuthorDao;
import dao.BookDao;
import dao.LendingDao;
import dao.VisitorDao;
import model.Author;
import model.Book;
import model.Lending;
import model.Visitor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/showorders")
public class ShowOrdersServlet extends HttpServlet {
    private LendingDao lendingDao;
    private AuthorDao authorDao;
    private BookDao bookDao;
    private VisitorDao visitorDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        lendingDao = new LendingDao();
        authorDao = new AuthorDao();
        bookDao = new BookDao();
        visitorDao = new VisitorDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lending> lendings = lendingDao.getUnconfirmedAll();
        List<Book> books = bookDao.getAll();
        List<Author> authors = authorDao.getAll();
        List<Visitor> visitors = visitorDao.getAll();

        req.setAttribute("lendings" ,lendings);
        req.setAttribute("books", books);
        req.setAttribute("authors", authors);
        req.setAttribute("visitors", visitors);
        req.getRequestDispatcher("/WEB-INF/showOrders.jsp").forward(req, resp);
    }

    @Override
    public void destroy() { super.destroy(); }
}
