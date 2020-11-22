package dao;

import connection.PoolConnectionBuilder;
import model.Author;
import model.Book;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao implements DaoInterface<Author> {
    private Connection connection;

    public AuthorDao() { }

    private void setAuthor(ResultSet resultSet, Author author) throws SQLException {
        author.setAuthorId(Long.parseLong(resultSet.getString("id_author")));
        author.setName(resultSet.getString("name"));
        author.setSurname(resultSet.getString("surname"));
        author.setPatronymic(resultSet.getString("patronymic"));
        author.setFullName(author.getName().charAt(0) + "."
                            + author.getPatronymic().charAt(0) + "."
                            + author.getSurname());
    }

    @Override
    public Author get(long id) {
        Author author = new Author();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.author" +
                    " where `library_demo`.author.id_author = " + id + ";");

            if (resultSet.next()) setAuthor(resultSet, author);
        }
        catch (SQLException ex) {
            System.out.println("Не удалось получить данные");
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Не удалось закрыть соединение");
                System.out.println(ex.getMessage());
            }
        }

        return author;
    }

    @Override
    public List<Author> getAll() {
        List<Author> authors = new ArrayList<>();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.author");

            while(resultSet.next()) {
                Author author = new Author();
                setAuthor(resultSet, author);
                authors.add(author);
            }
        }
        catch (SQLException ex) {
            System.out.println("Не удалось получить данные");
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Не удалось закрыть соединение");
                System.out.println(ex.getMessage());
            }
        }

        return authors;
    }

    @Override
    public void save(Author author) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO `library_demo`.author " + "VALUE ("
                    + author.getAuthorId() + ", '"
                    + author.getSurname() + "', '"
                    + author.getName() + "', '"
                    + author.getPatronymic() + "');");
        }
        catch (SQLException ex) {
            System.out.println("Не удалось создать запрос к базе данных");
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Не удалось закрыть соединение");
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void update(Author author) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE `library_demo`.author" + " SET "
                    + "`library_demo`.author.id_author=" + author.getAuthorId() + ", "
                    + "`library_demo`.author.surname='" + author.getSurname() + "', "
                    + "`library_demo`.author.name='" + author.getName() + "', "
                    + "`library_demo`.author.patronymic='" + author.getPatronymic() + "'"
                    + "WHERE `library_demo`.author.id_author=" + author.getAuthorId());
        }
        catch (SQLException ex) {
            System.out.println("Не удалось создать запрос к базе данных");
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Не удалось закрыть соединение");
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public void delete(Author author) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM `library_demo`.author "
                    + "WHERE `library_demo`.author.id_author=" + author.getAuthorId());
        }
        catch (SQLException ex) {
            System.out.println("Не удалось создать запрос к базе данных");
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Не удалось закрыть соединение");
                System.out.println(ex.getMessage());
            }
        }
    }
}
