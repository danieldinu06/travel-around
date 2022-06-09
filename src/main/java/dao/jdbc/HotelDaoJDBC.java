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

            addImages(hotel.getId(), hotel.getImages());
            connectHotel(hotel.getTouristAttraction().getId(), hotel.getId());
        }
        catch (SQLException e) {
            throw new RuntimeException("Error while adding new Hotel");
        }
    }

    public void connectHotel(Integer touristAttractionId, Integer hotelId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO ta_h_seq (hotel_id, t_attraction_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, hotelId);
            statement.setInt(2, touristAttractionId);

            statement.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addImages(Integer hotelId, List<String> images) {
        try (Connection connection = dataSource.getConnection()) {
            for (String image: images) {
                String sql = "INSERT INTO hotel_images (image) VALUES(?)";
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, image);
                statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();

                int firstIndex = resultSet.getInt(1);

                connectImages(hotelId, firstIndex);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting Hotel Images.");
        }
    }

    public void connectImages(Integer hotelId, int index) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO h_i_seq (hotel_id, hotel_image_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, hotelId);
            statement.setInt(2, index);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting Hotel Images.");
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

            List<String> images = getImages(resultSet.getInt(1));

            String name = resultSet.getString(1);
            String description = resultSet.getString(2);
            float rating = resultSet.getFloat(3);
            int rooms_number = resultSet.getInt(4);

            Hotel hotel = new Hotel(name, description, rating, rooms_number, touristAttraction);
            hotel.setId(id);
            hotel.setImages(images);

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
    public List<Hotel> getHotelsByTouristAttraction(Integer touristAttractionId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM hotels\n" +
                    "INNER JOIN ta_h_seq tahs on hotels.id = tahs.hotel_id\n" +
                    "LEFT OUTER JOIN tourist_attractions ta on ta.id = tahs.t_attraction_id\n" +
                    "WHERE ta.id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, touristAttractionId);

            ResultSet resultSet = statement.executeQuery();

            List<Hotel> result = new ArrayList<>();
            while(resultSet.next()) {
                TouristAttraction touristAttraction = touristAttractionDao.get(getTouristAttractionId(resultSet.getInt(1)));

                List<String> images = getImages(resultSet.getInt(1));

                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                float rating = resultSet.getFloat(4);
                int rooms_number = resultSet.getInt(5);


                Hotel hotel = new Hotel(name, description, rating, rooms_number, touristAttraction);
                hotel.setId(resultSet.getInt(1));
                hotel.setImages(images);

                result.add(hotel);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting Hotel");
        }
    }

    public List<String> getImages(Integer touristAttractionId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT image FROM hotel_images\n" +
                    "INNER JOIN h_i_seq his on hotel_images.id = his.hotel_image_id\n" +
                    "LEFT OUTER JOIN hotels h on h.id = his.hotel_id\n" +
                    "WHERE h.id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, touristAttractionId);
            ResultSet resultSet = statement.executeQuery();

            List<String> result = new ArrayList<>();
            while(resultSet.next()) {
                String image = resultSet.getString(1);
                result.add(image);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting Hotel Images.");
        }
    }

}