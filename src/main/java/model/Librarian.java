package model;

public class Librarian {
    private long librarianId;
    private long userId;
    private String surname;
    private String name;
    private String patronymic;
    private String experience;
    private String fullName;

    public long getLibrarianId() { return librarianId; }

    public void setLibrarianId(long librarianId) { this.librarianId = librarianId; }

    public long getUserId() { return userId; }

    public void setUserId(long userId) { this.userId = userId; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPatronymic() { return patronymic; }

    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

    public String getExperience() { return experience; }

    public void setExperience(String experience) { this.experience = experience; }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }
}
