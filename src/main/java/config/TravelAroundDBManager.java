package config;

import dao.HotelDao;
import dao.RestaurantDao;
import dao.TouristAttractionDao;
import dao.jdbc.HotelDaoJDBC;
import dao.jdbc.RestaurantDaoJDBC;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;

public class TravelArroundDBManager {
    public TravelArroundDBManager() {

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
