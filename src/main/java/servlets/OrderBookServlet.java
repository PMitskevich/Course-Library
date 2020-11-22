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

@WebServlet("/orderbook")
public class OrderBookServlet extends HttpServlet {
    private AuthorDao authorDao;
    private BookDao bookDao;
    private PublishingDao publishingDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        authorDao = new AuthorDao();
        bookDao = new BookDao();
        publishingDao = new PublishingDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Book book = bookDao.get(id);
        Author author = authorDao.get(book.getAuthorId());
        Publishing publishing = publishingDao.get(book.getPublishingId());
        req.setAttribute("book", book);
        req.setAttribute("author", author);
        req.setAttribute("publishing", publishing);
        req.getRequestDispatcher("/WEB-INF/orderInfo.jsp").forward(req, resp);
    }

    @Override
    public void destroy() { super.destroy(); }
}
