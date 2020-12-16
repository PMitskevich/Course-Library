package dao;

import connection.PoolConnectionBuilder;
import model.Author;
import model.Book;
import model.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao implements DaoInterface<Author> {
    private static final Logger LOGGER = LogManager.getLogger(AuthorDao.class);
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
            LOGGER.log(Level.ERROR, "Не удалось создать запрос к базе данных: "
                    + ex.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                LOGGER.log(Level.ERROR, "Не удалось закрыть соединение: " + ex.getMessage());
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
            LOGGER.log(Level.ERROR, "Не удалось создать запрос к базе данных: "
                    + ex.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                LOGGER.log(Level.ERROR, "Не удалось закрыть соединение: " + ex.getMessage());
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
            LOGGER.log(Level.ERROR, "Не удалось создать запрос к базе данных: "
                    + ex.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                LOGGER.log(Level.ERROR, "Не удалось закрыть соединение: " + ex.getMessage());
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
            LOGGER.log(Level.ERROR, "Не удалось создать запрос к базе данных: "
                    + ex.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                LOGGER.log(Level.ERROR, "Не удалось закрыть соединение: " + ex.getMessage());
            }
        }
    }

    @Override
    public void delete(long id) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM `library_demo`.author "
                    + "WHERE `library_demo`.author.id_author=" + id);
        }
        catch (SQLException ex) {
            LOGGER.log(Level.ERROR, "Не удалось создать запрос к базе данных: "
                    + ex.getMessage());
        }
        finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                LOGGER.log(Level.ERROR, "Не удалось закрыть соединение: " + ex.getMessage());
            }
        }
    }
}
