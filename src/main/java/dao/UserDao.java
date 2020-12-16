package dao;

import connection.PoolConnectionBuilder;
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

public class UserDao implements DaoInterface<User> {
    private static final Logger LOGGER = LogManager.getLogger(UserDao.class);
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
            statement.executeUpdate("DELETE FROM `library_demo`.user "
                    + "WHERE `library_demo`.user.id_user=" + id);
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
