package config;

import dao.HotelDao;
import dao.RestaurantDao;
import dao.RoomDao;
import dao.TouristAttractionDao;
import dao.jdbc.HotelDaoJDBC;
import dao.jdbc.RestaurantDaoJDBC;
import dao.jdbc.RoomDaoJDBC;
import dao.jdbc.TouristAttractionDaoJDBC;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ApplicationService {
    RoomDao roomDao;
    HotelDao hotelDao;
    RestaurantDao restaurantDao;
    TouristAttractionDao touristAttractionDao;
    TravelAroundDBManager travelAroundDBManager;
    DataSource dataSource;

    public ApplicationService() {
        travelAroundDBManager = new TravelAroundDBManager();

        try {
            dataSource = travelAroundDBManager.run();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        restaurantDao = new RestaurantDaoJDBC(dataSource);
        roomDao = new RoomDaoJDBC(dataSource);
        hotelDao = new HotelDaoJDBC(dataSource, roomDao);
        //touristAttractionDao = new TouristAttractionDaoJDBC(dataSource, hotelDao, restaurantDao);
    }
}
