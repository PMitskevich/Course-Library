package servlets;

import com.google.gson.Gson;
import dao.AuthorDao;
import dao.BookDao;
import dao.LendingDao;
import model.Author;
import model.Book;
import model.Lending;
import services.statistics.Statistics;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/statistics")
public class StatisticsServlet extends HttpServlet {
    private BookDao bookDao;
    private LendingDao lendingDao;
    private AuthorDao authorDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bookDao = new BookDao();
        lendingDao = new LendingDao();
        authorDao = new AuthorDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookDao.getAll();
        List<Lending> lendings = lendingDao.getAll();
        List<Author> authors = authorDao.getAll();
        req.setAttribute("books", books);
        req.setAttribute("lendings", lendings);
        req.setAttribute("authors", authors);
        req.getRequestDispatcher("/WEB-INF/statistics.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String timePeriod = req.getParameter("filter");
        List<Lending> lendings = lendingDao.findByPeriod(timePeriod);
        Map<String, Integer> genresAndAmount = new Statistics().createStatisticsByGenres(lendings);
        req.setAttribute("period", timePeriod);
        req.setAttribute("genresmap", genresAndAmount);
        req.getRequestDispatcher("/WEB-INF/statisticsResult.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
