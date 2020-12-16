package servlets;

import dao.AuthorDao;
import dao.BookDao;
import dao.LendingDao;
import dao.PublishingDao;
import model.Book;
import model.Lending;
import services.finderbyname.BookFinderByName;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/addlending")
public class AddLendingServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        long roleId = (long) session.getAttribute("id_visitor");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        Book book = new BookFinderByName().getByName(req.getParameter("bookname"));
        LendingDao lendingDao = new LendingDao();
        List<Lending> lendings = lendingDao.getAll();
        Lending lending = new Lending();

        lending.setLendingId(lendingDao.getMaxId() + 1);
        lending.setLendQuantity(quantity);
        lending.setLendDate(LocalDate.now());
        lending.setVisitorId(roleId);
        lending.setBookId(book.getBookId());

        lendingDao.save(lending);
        req.getRequestDispatcher("/WEB-INF/userMenu.jsp").forward(req, resp);
    }

    @Override
    public void destroy() { super.destroy(); }
}
