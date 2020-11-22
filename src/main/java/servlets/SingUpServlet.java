package servlets;

import dao.UserDao;
import dao.VisitorDao;
import model.User;
import model.Visitor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SingUpServlet extends HttpServlet {
    private UserDao userDao;
    private VisitorDao visitorDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userDao = new UserDao();
        visitorDao = new VisitorDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = new User();
        Visitor visitor = new Visitor();

        user.setUser(req);
        visitor.setVisitor(req);
        userDao.save(user);
        visitorDao.save(visitor);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void destroy() { super.destroy(); }
}
