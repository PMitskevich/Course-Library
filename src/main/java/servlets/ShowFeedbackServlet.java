package servlets;

import dao.FeedbackDao;
import dao.LibrarianDao;
import dao.UserDao;
import dao.VisitorDao;
import model.Feedback;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/showfeedback")
public class ShowFeedbackServlet extends HttpServlet {
    private UserDao userDao;
    private FeedbackDao feedbackDao;
    private VisitorDao visitorDao;
    private LibrarianDao librarianDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userDao = new UserDao();
        feedbackDao = new FeedbackDao();
        visitorDao = new VisitorDao();
        librarianDao = new LibrarianDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userDao.getAll();
        List<Feedback> feedbacks = feedbackDao.getAll();
        req.setAttribute("users", users);
        req.setAttribute("feedbacks", feedbacks);
        req.getRequestDispatcher("/WEB-INF/feedbacks.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        String feedbackText = req.getParameter("feedbackText");
        List<Feedback> feedbacks = feedbackDao.getAll();
        List<User> users = userDao.getAll();
        Long visitorId = (Long) session.getAttribute("id_visitor");
        Long librarianId = (Long) session.getAttribute("id_librarian");
        long userId = 0;

        if (visitorId != -1) {
            userId = visitorDao.get(visitorId).getUserId();
        }
        else {
            userId = librarianDao.get(librarianId).getUserId();
        }
        Feedback feedback = new Feedback(feedbacks.size() + 1, userId, feedbackText);
        feedbackDao.save(feedback);
        req.setAttribute("feedbacks", feedbacks);
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/feedbacks.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
