package connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnectionBuilder implements ConnectionBuilder {
    private static PoolConnectionBuilder poolConnectionBuilder;
    private DataSource dataSource;

    private PoolConnectionBuilder() {
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/library");

        }
        catch (NamingException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static PoolConnectionBuilder getInstance() {
        if (poolConnectionBuilder == null) poolConnectionBuilder = new PoolConnectionBuilder();
        return poolConnectionBuilder;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
