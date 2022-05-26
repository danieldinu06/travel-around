package config;

import dao.HotelDao;
import dao.RestaurantDao;
import dao.TouristAttractionDao;
import dao.jdbc.HotelDaoJDBC;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;

public class TravelArroundDBManager {
    HotelDao hotelDao;
    RestaurantDao restaurantDao;
    TouristAttractionDao touristAttractionDao;

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

    public void run() throws SQLException {
        try {
            setup();
        } catch (SQLException throwables) {
            System.err.println("Could not connect to the database.");
            return;
        }
    }

    private void setup() throws SQLException {
        DataSource dataSource = connect();
//        touristAttractionDao = new TouristAttractionJDBC(dataSource);
//        hotelDao = new HotelDaoJDBC(dataSource, touristAttractionDao);
//        restaurantDao = new RestaurantDaoJDBC(dataSource, hotelDao);
//        roomDao = new RoomDaoJDBC(dataSource, hotelDao);
    }
}
