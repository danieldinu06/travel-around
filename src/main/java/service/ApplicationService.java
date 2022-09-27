package service;

import config.TravelAroundDBManager;
import dao.*;
import dao.jdbc.*;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ApplicationService {
    RoomDao roomDao;
    public HotelDao hotelDao;
    public RestaurantDao restaurantDao;
    public TouristAttractionDao touristAttractionDao;
    public UserDao userDao;
    TravelAroundDBManager travelAroundDBManager;
    DataSource dataSource;
    private static ApplicationService instance = null;

    public void setupDAOs() {
        touristAttractionDao = new TouristAttractionDaoJDBC(dataSource);
        restaurantDao = new RestaurantDaoJDBC(dataSource, touristAttractionDao);
        hotelDao = new HotelDaoJDBC(dataSource, touristAttractionDao);
        roomDao = new RoomDaoJDBC(dataSource, hotelDao);
        userDao = new UserDaoJDBC(dataSource);
    }

    private ApplicationService() {
        travelAroundDBManager = new TravelAroundDBManager();

        try {
            dataSource = travelAroundDBManager.run();
            setupDAOs();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ApplicationService getInstance() {
        if (instance == null) {
            instance = new ApplicationService();
        }

        return instance;
    }
}
