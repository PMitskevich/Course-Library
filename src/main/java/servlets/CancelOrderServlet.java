package servlets;

import dao.AuthorDao;
import dao.BookDao;
import dao.LendingDao;
import dao.LibrarianDao;
import model.Author;
import model.Book;
import model.Lending;
import model.Librarian;
import services.finderbyid.BookFinderById;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/cancelorder")
public class CancelOrderServlet extends HttpServlet {
    private LendingDao lendingDao;
    private BookDao bookDao;
    private AuthorDao authorDao;
    private LibrarianDao librarianDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        lendingDao = new LendingDao();
        bookDao = new BookDao();
        authorDao = new AuthorDao();
        librarianDao = new LibrarianDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        long visitorId = (long) session.getAttribute("id_visitor");
        List<Lending> lendings = lendingDao.getAllForVisitor(visitorId);
        List<Author> authors = authorDao.getAll();
        List<Book> books = bookDao.getAll();
        Librarian librarian = librarianDao.get(1);

        req.setAttribute("lendings", lendings);
        req.setAttribute("authors", authors);
        req.setAttribute("books", books);
        req.setAttribute("librarian", librarian);
        req.getRequestDispatcher("/WEB-INF/cancelOrder.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long lendingId = Long.parseLong(req.getParameter("lendingId"));
        Lending lending = lendingDao.get(lendingId);
        Book book = new BookFinderById().getByLending(lending);
        book.setQuantity(book.getQuantity() + lending.getLendQuantity());
        bookDao.update(book);
        lendingDao.delete(lendingId);
        req.getRequestDispatcher("/WEB-INF/userMenu.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
