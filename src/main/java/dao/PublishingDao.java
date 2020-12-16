package dao;

import connection.PoolConnectionBuilder;
import model.Publishing;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PublishingDao implements DaoInterface<Publishing> {
    private static final Logger LOGGER = LogManager.getLogger(PublishingDao.class);
    private Connection connection;

    public PublishingDao() { }

    private void setPublishing(ResultSet resultSet, Publishing publishing) throws SQLException {
        publishing.setPublishingId(Long.parseLong(resultSet.getString("id_publishing")));
        publishing.setPublishingName(resultSet.getString("name"));
    }

    @Override
    public Publishing get(long id) {
        Publishing publishing = new Publishing();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.publishing" +
                    " where `library_demo`.publishing.id_publishing = " + id + ";");

            if (resultSet.next()) setPublishing(resultSet, publishing);
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
        return publishing;
    }

    @Override
    public List<Publishing> getAll() {
        List<Publishing> publishings = new ArrayList<>();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.publishing");

            while(resultSet.next()) {
                Publishing publishing = new Publishing();
                setPublishing(resultSet, publishing);
                publishings.add(publishing);
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

        return publishings;
    }

    @Override
    public void save(Publishing publishing) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO `library_demo`.publishing " + "VALUE ("
                    + publishing.getPublishingId() + ", '"
                    + publishing.getPublishingName() + "');");
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
    public void update(Publishing publishing) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE `library_demo`.publishing" + " SET "
                    + "`library_demo`.publishing.id_publishing=" + publishing.getPublishingId() + ", "
                    + "`library_demo`.publishing.name='" + publishing.getPublishingName() + "'"
                    + "WHERE `library_demo`.publishing.id_publishing=" + publishing.getPublishingId());
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
            statement.executeUpdate("DELETE FROM `library_demo`.publishing "
                    + "WHERE `library_demo`.publishing.id_publishing=" + id);
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
