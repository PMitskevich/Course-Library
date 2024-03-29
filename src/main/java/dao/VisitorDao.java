package dao;

import connection.PoolConnectionBuilder;
import model.Visitor;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VisitorDao implements DaoInterface<Visitor> {
    private static final Logger LOGGER = LogManager.getLogger(VisitorDao.class);
    private Connection connection;

    public VisitorDao() { }

    private void setVisitor(ResultSet resultSet, Visitor visitor) throws SQLException {
        visitor.setVisitorId(Integer.parseInt(resultSet.getString("id_visitor")));
        visitor.setUserId(Integer.parseInt(resultSet.getString("User_id_user")));
        visitor.setName(resultSet.getString("name"));
        visitor.setSurname(resultSet.getString("surname"));
        visitor.setPatronymic(resultSet.getString("patronymic"));
        visitor.setPhone(resultSet.getString("phone"));
        visitor.setBirthday(LocalDate.parse(resultSet.getString("birthday")));
        visitor.setFullName(visitor.getName().charAt(0) + "."
                + visitor.getPatronymic().charAt(0) + "."
                + visitor.getSurname());
    }

    @Override
    public Visitor get(long id) {
        Visitor visitor = new Visitor();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.visitor" +
                    " where `library_demo`.visitor.id_visitor = " + id + ";");

            if (resultSet.next()) setVisitor(resultSet, visitor);
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
        return visitor;
    }

    @Override
    public List<Visitor> getAll() {
        List<Visitor> visitors = new ArrayList<>();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.visitor");

            while(resultSet.next()) {
                Visitor visitor = new Visitor();
                setVisitor(resultSet, visitor);
                visitors.add(visitor);
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

        return visitors;
    }

    @Override
    public void save(Visitor visitor) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO `library_demo`.visitor " + "VALUE ("
                    + visitor.getVisitorId() + ", '"
                    + visitor.getSurname() + "', '"
                    + visitor.getName() + "', '"
                    + visitor.getPatronymic() + "', '"
                    + visitor.getPhone() + "', '"
                    + visitor.getBirthday() + "', '"
                    + visitor.getUserId() + "');");
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
    public void update(Visitor visitor) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE `library_demo`.visitor" + " SET "
                    + "`library_demo`.visitor.id_visitor=" + visitor.getVisitorId() + ", "
                    + "`library_demo`.visitor.surname='" + visitor.getSurname() + "', "
                    + "`library_demo`.visitor.name='" + visitor.getName() + "', "
                    + "`library_demo`.visitor.patronymic='" + visitor.getPatronymic() + "', "
                    + "`library_demo`.visitor.phone='" + visitor.getPhone() + "', "
                    + "`library_demo`.visitor.birthday='" + visitor.getBirthday() + "', "
                    + "`library_demo`.visitor.User_id_user='" + visitor.getUserId() + "'"
                    + "WHERE `library_demo`.visitor.id_visitor=" + visitor.getVisitorId());
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
            statement.executeUpdate("DELETE FROM `library_demo`.visitor "
                    + "WHERE `library_demo`.visitor.id_visitor=" + id);
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
