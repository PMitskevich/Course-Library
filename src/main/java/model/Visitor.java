package model;

import dao.UserDao;
import dao.VisitorDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;

public class Visitor {
    private long visitorId;
    private long userId;
    private String surname;
    private String name;
    private String patronymic;
    private String fullName;
    private LocalDate birthday;

    public long getVisitorId() { return visitorId; }

    public void setVisitorId(long visitorId) { this.visitorId = visitorId; }

    public long getUserId() { return userId; }

    public void setUserId(long userId) { this.userId = userId; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPatronymic() { return patronymic; }

    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public LocalDate getBirthday() { return birthday; }

    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public void setVisitor(HttpServletRequest req) throws IOException {
        this.setVisitorId(new VisitorDao().getAll().size() + 1);
        this.setSurname(req.getParameter("surname"));
        this.setName(req.getParameter("name"));
        this.setPatronymic(req.getParameter("patronymic"));
        this.setFullName(this.getName().charAt(0) + "."
                + this.getPatronymic().charAt(0) + "."
                + this.getSurname());
        this.setBirthday(LocalDate.parse(req.getParameter("birthday")));
        this.setUserId(new UserDao().getAll().size() + 1);
    }
}
