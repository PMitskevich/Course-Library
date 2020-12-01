package servlets;

import dao.BookDao;
import dao.LendingDao;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/statistics")
public class StatisticsServlet extends HttpServlet {
    private BookDao bookDao;
    private LendingDao lendingDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        long roleId = (long) session.getAttribute("id_visitor");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
