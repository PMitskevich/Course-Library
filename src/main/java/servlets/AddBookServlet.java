package servlets;

import dao.AuthorDao;
import dao.BookDao;
import dao.PublishingDao;
import model.Author;
import model.Book;
import model.Publishing;
import services.finderbyname.AuthorFinderByName;
import services.finderbyname.PublishingFinderByName;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addbook")
public class AddBookServlet extends HttpServlet {
    private AuthorDao authorDao;
    private PublishingDao publishingDao;
    private BookDao bookDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        authorDao = new AuthorDao();
        publishingDao = new PublishingDao();
        bookDao = new BookDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Author> authors = authorDao.getAll();
        List<Publishing> publishings = publishingDao.getAll();
        req.setAttribute("authors", authors);
        req.setAttribute("publishings", publishings);
        req.getRequestDispatcher("/WEB-INF/addBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Book book = new Book();
        Author author = new Author();
        Publishing publishing = new PublishingFinderByName().getByName(req.getParameter("publishing"));

        if (author.isNewAuthor(req.getParameter("author"))) {
            author.setAuthorId(authorDao.getAll().size() + 1);
            author.setAuthor(req.getParameter("author"));
            authorDao.save(author);
        }
        else author = new AuthorFinderByName().getByName(req.getParameter("author"));

        if (publishing == null) {
            publishing = new Publishing();
            publishing.setPublishingId(publishingDao.getAll().size() + 1);
            publishing.setPublishingName(req.getParameter("publishing"));
            publishingDao.save(publishing);
        }

        book.setBookId(bookDao.getAll().size() + 1);
        book.setBook(req, author, publishing);
        bookDao.save(book);

        req.getRequestDispatcher("/WEB-INF/adminMenu.jsp").forward(req, resp);
    }

    @Override
    public void destroy() { super.destroy(); }
}
