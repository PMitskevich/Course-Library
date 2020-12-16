package connection;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnectionBuilder implements ConnectionBuilder {
    private static final Logger LOGGER = LogManager.getLogger(PoolConnectionBuilder.class);
    private static PoolConnectionBuilder poolConnectionBuilder;
    private DataSource dataSource;

    private PoolConnectionBuilder() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/library");
        }
        catch (NamingException ex) {
            LOGGER.log(Level.ERROR, ex.getMessage());
        }
    }

    public static PoolConnectionBuilder getInstance() {
        if (poolConnectionBuilder == null) {
            poolConnectionBuilder = new PoolConnectionBuilder();
        }
        return poolConnectionBuilder;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
