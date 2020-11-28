package servlets;

import com.google.gson.Gson;
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
import java.net.URLDecoder;
import java.util.List;

@WebServlet("/findbooks")
public class FIndBooksServletDeprecated extends HttpServlet {
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
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        String search = URLDecoder.decode(req.getHeader("search"), "UTF-8");
        List<Book> books = bookDao.findBySubstring(search);
        Gson gson = new Gson();
        String booksAsJson = gson.toJson(books);
        resp.getWriter().write(booksAsJson);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
