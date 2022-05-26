package config;

import dao.HotelDao;
import dao.RestaurantDao;
import dao.TouristAttractionDao;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ApplicationService {
    HotelDao hotelDao;
    RestaurantDao restaurantDao;
    TouristAttractionDao touristAttractionDao
    TravelAroundDBManager travelAroundDBManager;
    DataSource dataSource;

    public ApplicationService() {
        travelAroundDBManager = new TravelAroundDBManager();

        try {
            dataSource = travelAroundDBManager.run();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        restaurantDao = new RestaurantDaoJDBC(dataSource);
//        roomDao = new RoomDaoJDBC(dataSource);
//        hotelDao = new HotelDaoJDBC(dataSource, roomDao);
//        touristAttractionDao = new TouristAttractionJDBC(dataSource, hotelDao, restaurantDao);
    }
}
