package dao;

import connection.PoolConnectionBuilder;
import model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements DaoInterface<User> {
    private Connection connection;

    public UserDao() { }

    private void setUser(ResultSet resultSet, User user) throws SQLException {
        user.setUserId(Integer.parseInt(resultSet.getString("id_user")));
        user.setAdmin(resultSet.getString("is_admin").equals("1"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
    }

    @Override
    public User get(long id) {
        User user = new User();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.user" +
                                                            " where `library_demo`.user.id_user = " + id + ";");

            if (resultSet.next()) setUser(resultSet, user);
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

        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.user");

            while(resultSet.next()) {
                User user = new User();
                setUser(resultSet, user);
                users.add(user);
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

        return users;
    }

    @Override
    public void save(User user) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO `library_demo`.user " + "VALUE ("
                    + user.getUserId() + ", '"
                    + user.getLogin() + "', '"
                    + user.getPassword() + "', '"
                    + (user.isAdmin() ? 1 : 0) + "');");
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
    public void update(User user) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE `library_demo`.user" + " SET "
                    + "`library_demo`.user.id_user=" + user.getUserId() + ", "
                    + "`library_demo`.user.login='" + user.getLogin() + "', "
                    + "`library_demo`.user.password='" + user.getPassword() + "', "
                    + "`library_demo`.user.is_admin=" + user.isAdmin()
                    + " WHERE `library_demo`.user.id_user=" + user.getUserId());
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
    public void delete(User user) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM `library_demo`.user "
                    + "WHERE `library_demo`.user.id_user=" + user.getUserId());
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
