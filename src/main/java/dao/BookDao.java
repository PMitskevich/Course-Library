package dao;

import connection.PoolConnectionBuilder;
import model.Book;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements DaoInterface<Book> {
    private Connection connection;

    public BookDao() { }

    private void setBook(ResultSet resultSet, Book book) throws SQLException {
        book.setBookId(Integer.parseInt(resultSet.getString("id_book")));
        book.setAuthorId(Integer.parseInt(resultSet.getString("id_author")));
        book.setPublishingId(Integer.parseInt(resultSet.getString("id_publishing")));
        book.setGenre(resultSet.getString("genre"));
        book.setName(resultSet.getString("name"));
        book.setQuantity(Integer.parseInt(resultSet.getString("quantity")));
    }

    @Override
    public Book get(long id) {
        Book book = new Book();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.book" +
                    " where `library_demo`.book.id_book = " + id + ";");

            if (resultSet.next()) setBook(resultSet, book);
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
        return book;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.book");

            while(resultSet.next()) {
                Book book = new Book();
                setBook(resultSet, book);
                books.add(book);
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

        return books;
    }

    @Override
    public void save(Book book) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO `library_demo`.book " + "VALUE ("
                    + book.getBookId() + ", '"
                    + book.getName() + "', '"
                    + book.getAuthorId() + "', '"
                    + book.getGenre() + "', '"
                    + book.getPublishingId() + "', '"
                    + book.getQuantity() + "');");
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
    public void update(Book book) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE `library_demo`.book" + " SET "
                    + "`library_demo`.book.id_book=" + book.getBookId() + ", "
                    + "`library_demo`.book.name='" + book.getName() + "', "
                    + "`library_demo`.book.id_author='" + book.getAuthorId() + "', "
                    + "`library_demo`.book.genre='" + book.getGenre() + "', "
                    + "`library_demo`.book.id_publishing='" + book.getPublishingId() + "', "
                    + "`library_demo`.book.quantity='" + book.getQuantity() + "'"
                    + "WHERE `library_demo`.book.id_book=" + book.getBookId());
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
    public void delete(Book book) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM `library_demo`.book "
                    + "WHERE `library_demo`.book.id_book=" + book.getBookId());
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