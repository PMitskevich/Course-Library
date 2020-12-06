package model;

import dao.UserDao;

import javax.servlet.http.HttpServletRequest;

public class User {
    private long userId;
    private String login;
    private String password;
    private boolean isAdmin;

    public long getUserId() { return userId; }

    public void setUserId(long userId) { this.userId = userId; }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public boolean isAdmin() { return isAdmin; }

    public boolean getIsAdmin() { return isAdmin; }

    public void setAdmin(boolean admin) { isAdmin = admin; }

    public void setUser(HttpServletRequest req) {
        this.setUserId(new UserDao().getAll().size() + 1);
        this.setAdmin(false);
        this.setLogin(req.getParameter("login"));
        this.setPassword(req.getParameter("password"));
    }
}
