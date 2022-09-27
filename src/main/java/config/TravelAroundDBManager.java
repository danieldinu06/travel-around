package config;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class TravelAroundDBManager {
    public TravelAroundDBManager() {

    }

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        ApplicationProperties properties = new ApplicationProperties();

        dataSource.setServerName(properties.readProperties("server"));
        dataSource.setUser(properties.readProperties("user"));
        dataSource.setPassword(properties.readProperties("password"));
        dataSource.setDatabaseName(properties.readProperties("database"));

        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection OK!");

        return dataSource;
    }

    public DataSource run() throws SQLException {
        try {
            return connect();
        } catch (SQLException throwables) {
            System.err.println("Could not connect to the database.");
        }
        return null;
    }
}
