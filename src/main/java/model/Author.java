package model;

public class Author {
    private long authorId;
    private String name;
    private String surname;
    private String patronymic;
    private String fullName;

    public long getAuthorId() { return authorId; }

    public void setAuthorId(long authorId) { this.authorId = authorId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getPatronymic() { return patronymic; }

    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }
}
