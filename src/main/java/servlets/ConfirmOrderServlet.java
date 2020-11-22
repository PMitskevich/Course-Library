package servlets;

import dao.LendingDao;
import model.Author;
import model.Book;
import model.Lending;
import model.Visitor;
import services.finderbyname.AuthorFinderByName;
import services.finderbyname.BookFinderByName;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/orderinfo")
public class ConfirmOrderServlet extends HttpServlet {
    private LendingDao lendingDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        lendingDao = new LendingDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Book book = new BookFinderByName().getByName(req.getParameter("bookname"));
        long visitorId = Long.parseLong(req.getParameter("visitorid"));
        LocalDate lendDate = LocalDate.parse(req.getParameter("lenddate"));
        LocalDate returnDate = LocalDate.parse(req.getParameter("returndate"));
        int lendQuantity = Integer.parseInt(req.getParameter("lendquantity"));
        Lending lending = new Lending();

        lending.setLendingId(Long.parseLong(req.getParameter("lendingid")));
        lending.setBookId(book.getBookId());
        lending.setVisitorId(visitorId);
        lending.setLibrarianId(1);
        lending.setLendDate(lendDate);
        lending.setReturnDate(returnDate);
        lending.setLendQuantity(lendQuantity);

        lendingDao.update(lending);
        req.getRequestDispatcher("/WEB-INF/adminMenu.jsp").forward(req, resp);
    }

    @Override
    public void destroy() { super.destroy(); }
}
