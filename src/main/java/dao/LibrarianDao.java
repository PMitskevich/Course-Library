package dao;

import connection.PoolConnectionBuilder;
import model.Librarian;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibrarianDao implements DaoInterface<Librarian> {
    private Connection connection;

    public LibrarianDao() { }

    private void setLibrarian(ResultSet resultSet, Librarian librarian) throws SQLException {
        librarian.setLibrarianId(Integer.parseInt(resultSet.getString("id_librarian")));
        librarian.setSurname(resultSet.getString("surname"));
        librarian.setName(resultSet.getString("name"));
        librarian.setPatronymic(resultSet.getString("patronymic"));
        librarian.setExperience(resultSet.getString("experience"));
        librarian.setUserId(Integer.parseInt(resultSet.getString("User_id_user")));
        librarian.setFullName(librarian.getName().charAt(0) + "."
                + librarian.getPatronymic().charAt(0) + "."
                + librarian.getSurname());
    }

    @Override
    public Librarian get(long id) {
        Librarian librarian = new Librarian();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.librarian" +
                    " where `library_demo`.librarian.id_librarian = " + id + ";");

            if (resultSet.next()) setLibrarian(resultSet, librarian);
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
        return librarian;
    }

    @Override
    public List<Librarian> getAll() {
        List<Librarian> librarians = new ArrayList<>();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.librarian");

            while(resultSet.next()) {
                Librarian librarian = new Librarian();
                setLibrarian(resultSet, librarian);
                librarians.add(librarian);
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

        return librarians;
    }

    @Override
    public void save(Librarian librarian) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO `library_demo`.librarian " + "VALUE ("
                    + librarian.getLibrarianId() + ", '"
                    + librarian.getSurname() + "', '"
                    + librarian.getName() + "', '"
                    + librarian.getPatronymic() + "', '"
                    + librarian.getExperience() + "', '"
                    + librarian.getUserId() + "');");
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
    public void update(Librarian librarian) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE `library_demo`.librarian" + " SET "
                    + "`library_demo`.librarian.id_librarian=" + librarian.getLibrarianId() + ", "
                    + "`library_demo`.librarian.surname='" + librarian.getSurname() + "', "
                    + "`library_demo`.librarian.name='" + librarian.getName() + "', "
                    + "`library_demo`.librarian.patronymic='" + librarian.getPatronymic() + "', "
                    + "`library_demo`.librarian.experience='" + librarian.getExperience() + "', "
                    + "`library_demo`.librarian.User_id_user='" + librarian.getUserId() + "'"
                    + "WHERE `library_demo`.librarian.id_librarian=" + librarian.getLibrarianId());
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
            statement.executeUpdate("DELETE FROM `library_demo`.librarian "
                    + "WHERE `library_demo`.librarian.id_librarian=" + id);
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
