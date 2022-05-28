package dao.jdbc;

import dao.HotelDao;
import dao.RoomDao;
import dao.TouristAttractionDao;
import model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class HotelDaoJDBC implements HotelDao {
    private static final Logger logger = LoggerFactory.getLogger(HotelDaoJDBC.class);
    private final DataSource dataSource;
    private static HotelDao instance;
    private TouristAttractionDao touristAttractionDao;

    public HotelDaoJDBC(DataSource dataSource, TouristAttractionDao touristAttractionDao) {
        this.dataSource = dataSource;
        this.touristAttractionDao = touristAttractionDao;
    }

    public static HotelDao getInstance(DataSource dataSource, TouristAttractionDao touristAttractionDao) {
        if (instance == null) {
            instance = new HotelDaoJDBC(dataSource, touristAttractionDao);
        }
        return instance;
    }

    public static HotelDao getInstance() {
        return instance;
    }

    @Override
    public void add(Hotel hotel) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO hotels (name, image, description, rating, rooms_number) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getImage());
            statement.setString(3, hotel.getDescription());
            statement.setFloat(4, hotel.getRating());
            statement.setInt(5, hotel.getRooms_number());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            hotel.setId(resultSet.getInt(1));


        }
        catch (SQLException e) {
            throw new RuntimeException("Error while adding new Tourist Attractions");
        }
    }

    @Override
    public Hotel get(String name) {
        return null;
    }

    @Override
    public List<Hotel> getHotelsByTouristAttraction() {
        return null;
    }

}