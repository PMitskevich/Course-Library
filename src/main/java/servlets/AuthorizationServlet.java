package servlets;

import connection.PoolConnectionBuilder;
import dao.UserDao;
import model.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import services.finderbyid.LibrarianFinderById;
import services.finderbyid.VisitorFinderById;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        List<User> users = userDao.getAll();

        for (User user: users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                if (!user.isAdmin()) {
                    long visitorId = new VisitorFinderById().getById(user.getUserId());
                    session.setAttribute("id_visitor", visitorId);
                    session.setAttribute("id_librarian", (long) -1);
                    req.getRequestDispatcher("/WEB-INF/userMenu.jsp").forward(req, resp);
                }
                else {
                    long librarianId = new LibrarianFinderById().getById(user.getUserId());
                    session.setAttribute("id_librarian", librarianId);
                    session.setAttribute("id_visitor", (long) -1);
                    req.getRequestDispatcher("/WEB-INF/adminMenu.jsp").forward(req, resp);
                }
            }
        }

        String message = "Проверьте логин или пароль";
        req.setAttribute("message", message);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void destroy() { super.destroy(); }
}
