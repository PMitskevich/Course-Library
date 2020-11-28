package dao;

import connection.PoolConnectionBuilder;
import model.Book;
import model.Lending;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LendingDao implements DaoInterface<Lending> {
    private Connection connection;

    public LendingDao() {}

    private void setLending(ResultSet resultSet, Lending lending) throws SQLException {
        lending.setLendingId(Long.parseLong(resultSet.getString("id_lending")));
        lending.setBookId(Long.parseLong(resultSet.getString("id_book")));
        lending.setVisitorId(Long.parseLong(resultSet.getString("id_visitor")));
        lending.setLibrarianId(Long.parseLong(resultSet.getString("id_librarian")));
        lending.setLendDate(LocalDate.parse(resultSet.getString("lend_date")));
        lending.setReturnDate(LocalDate.parse(resultSet.getString("return_date")));
        lending.setLendQuantity(Integer.parseInt(resultSet.getString("lend_quantity")));
    }

    public List<Lending> getConfirmed(long visitorId) {
        List<Lending> lendings = new ArrayList<>();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.lending" +
                    " where `library_demo`.lending.return_date <> '0000-01-01' and id_visitor = " + visitorId + ";");

            while(resultSet.next()) {
                Lending lending = new Lending();
                setLending(resultSet, lending);
                lendings.add(lending);
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

        return lendings;
    }

    public List<Lending> getUnconfirmed(long visitorId) {
        List<Lending> lendings = new ArrayList<>();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.lending" +
                    " where `library_demo`.lending.return_date = '0000-01-01' and id_visitor = " + visitorId + ";");

            while(resultSet.next()) {
                Lending lending = new Lending();
                setLending(resultSet, lending);
                lendings.add(lending);
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

        return lendings;
    }

    public List<Lending> getUnconfirmedAll() {
        List<Lending> lendings = new ArrayList<>();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.lending" +
                    " where `library_demo`.lending.return_date = '0000-01-01'");

            while(resultSet.next()) {
                Lending lending = new Lending();
                setLending(resultSet, lending);
                lendings.add(lending);
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

        return lendings;
    }

    @Override
    public Lending get(long id) {
        Lending lending = new Lending();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.lending" +
                    " where `library_demo`.lending.id_lending = " + id + ";");

            if (resultSet.next()) setLending(resultSet, lending);
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

        return lending;
    }

    @Override
    public List<Lending> getAll() {
        List<Lending> lendings = new ArrayList<>();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.lending");

            while(resultSet.next()) {
                Lending lending = new Lending();
                setLending(resultSet, lending);
                lendings.add(lending);
            }
        }
        catch (SQLException ex) {
            System.out.println("Метод getAll()");
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

        return lendings;
    }

    @Override
    public void save(Lending lending) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO `library_demo`.lending " + "VALUE ("
                    + lending.getLendingId() + ", '"
                    + lending.getBookId() + "', '"
                    + lending.getVisitorId() + "', '"
                    + "1" + "', '"
                    + lending.getLendDate() + "', '"
                    + "0000-01-01" + "', '"
                    + lending.getLendQuantity() + "');");
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
    public void update(Lending lending) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE `library_demo`.lending" + " SET "
                    + "`library_demo`.lending.id_lending=" + lending.getLendingId() + ", "
                    + "`library_demo`.lending.id_book='" + lending.getBookId() + "', "
                    + "`library_demo`.lending.id_visitor='" + lending.getVisitorId() + "', "
                    + "`library_demo`.lending.id_librarian='" + lending.getLibrarianId() + "', "
                    + "`library_demo`.lending.lend_date='" + lending.getLendDate() + "', "
                    + "`library_demo`.lending.return_date='" + lending.getReturnDate() + "', "
                    + "`library_demo`.lending.lend_quantity='" + lending.getLendQuantity() + "'"
                    + "WHERE `library_demo`.lending.id_lending=" + lending.getLendingId());

            if (!lending.getReturnDate().toString().equals("0000-01-01")) {
                BookDao bookDao = new BookDao();
                Book book = bookDao.get(lending.getBookId());
                book.setQuantity(book.getQuantity() - lending.getLendQuantity());
                bookDao.update(book);
            }
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
    public void delete(long id) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM `library_demo`.lending "
                    + "WHERE `library_demo`.lending.id_lending=" + id);
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
