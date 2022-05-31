package dao.jdbc;

import dao.HotelDao;
import dao.RoomDao;
import dao.TouristAttractionDao;
import model.Hotel;
import model.TouristAttraction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDaoJDBC implements HotelDao {
    private final DataSource dataSource;
    private TouristAttractionDao touristAttractionDao;

    public HotelDaoJDBC(DataSource dataSource, TouristAttractionDao touristAttractionDao) {
        this.dataSource = dataSource;
        this.touristAttractionDao = touristAttractionDao;
    }

    @Override
    public void add(Hotel hotel) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO hotels (name, description, rating, rooms_number) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, hotel.getName());
            statement.setString(2, hotel.getDescription());
            statement.setFloat(3, hotel.getRating());
            statement.setInt(4, hotel.getRooms_number());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            hotel.setId(resultSet.getInt(1));


        }
        catch (SQLException e) {
            throw new RuntimeException("Error while adding new Hotel");
        }
    }

    @Override
    public Hotel get(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT name, description, rating, rooms_number FROM hotels WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) return null;

            TouristAttraction touristAttraction = touristAttractionDao.get(getTouristAttractionId(id));

            String name = resultSet.getString(1);
            String description = resultSet.getString(2);
            float rating = resultSet.getFloat(3);
            int rooms_number = resultSet.getInt(4);

            Hotel hotel = new Hotel(name, description, rating, rooms_number, touristAttraction);
            hotel.setId(id);

            return hotel;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting Hotel");
        }
    }

    public Integer getTouristAttractionId(Integer hotelId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT t_attraction_id FROM ta_h_seq WHERE hotel_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, hotelId);

            ResultSet resultSet = statement.executeQuery();
            if(!resultSet.next()) return null;

            return resultSet.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting Hotel");
        }
    }

    @Override
    public List<Hotel> getHotelsByTouristAttraction() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, name, description, rating, rooms_number FROM hotels;";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            List<Hotel> result = new ArrayList<>();
            while(resultSet.next()) {
                TouristAttraction touristAttraction = touristAttractionDao.get(getTouristAttractionId(resultSet.getInt(1)));

                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                float rating = resultSet.getFloat(4);
                int rooms_number = resultSet.getInt(5);

                Hotel hotel = new Hotel(name, description, rating, rooms_number, touristAttraction);
                hotel.setId(resultSet.getInt(1));

                result.add(hotel);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting Hotel");
        }
    }

}