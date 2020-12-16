package dao;

import connection.PoolConnectionBuilder;
import model.Book;
import model.Feedback;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDao implements DaoInterface<Feedback> {
    private static final Logger LOGGER = LogManager.getLogger(FeedbackDao.class);
    private Connection connection;

    public FeedbackDao() {
    }

    private void setFeedback(ResultSet resultSet, Feedback feedback) throws SQLException {
        feedback.setFeedbackId(Long.parseLong(resultSet.getString("id_feedback")));
        feedback.setUserId(Long.parseLong(resultSet.getString("id_user")));
        feedback.setFeedback(resultSet.getString("feedback"));
    }

    @Override
    public Feedback get(long id) {
        Feedback feedback = new Feedback();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.feedback" +
                    " where `library_demo`.feedback.id_feedback = " + id + ";");

            if (resultSet.next()) setFeedback(resultSet, feedback);
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
        return feedback;
    }

    @Override
    public List<Feedback> getAll() {
        List<Feedback> feedbacks = new ArrayList<>();

        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from `library_demo`.feedback");

            while(resultSet.next()) {
                Feedback feedback = new Feedback();
                setFeedback(resultSet, feedback);
                feedbacks.add(feedback);
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

        return feedbacks;
    }

    @Override
    public void save(Feedback feedback) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO `library_demo`.feedback " + "VALUE ("
                    + feedback.getFeedbackId() + ", "
                    + feedback.getUserId() + ", '"
                    + feedback.getFeedback() + "');");
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
    public void update(Feedback feedback) {
        try {
            connection = PoolConnectionBuilder.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE `library_demo`.feedback" + " SET "
                    + "`library_demo`.feedback.id_feedback=" + feedback.getFeedbackId() + ", "
                    + "`library_demo`.feedback.id_user='" + feedback.getUserId() + "', "
                    + "`library_demo`.feedback.feedback='" + feedback.getFeedback() + "'"
                    + "WHERE `library_demo`.feedback.id_feedback=" + feedback.getFeedbackId());
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
            statement.executeUpdate("DELETE FROM `library_demo`.feedback "
                    + "WHERE `library_demo`.feedback.id_feedback=" + id);
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
