package dao.jdbc;

import dao.RestaurantDao;
import dao.TouristAttractionDao;
import model.Restaurant;
import model.TouristAttraction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDaoJDBC implements RestaurantDao {
    private TouristAttractionDao touristAttractionDao;

    private final DataSource dataSource;

    public RestaurantDaoJDBC(DataSource dataSource, TouristAttractionDao touristAttractionDao) {
        this.dataSource = dataSource;
        this.touristAttractionDao = touristAttractionDao;
    }

    @Override
    public void add(Restaurant restaurant) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO restaurants (name, t_attraction_id, rating) VALUES(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, restaurant.getName());
            statement.setInt(2, restaurant.getTouristAttraction().getId());
            statement.setFloat(3, restaurant.getRating());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            restaurant.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException("Error while adding new Restaurant.");
        }
    }

    @Override
    public Restaurant get(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT name, t_attraction_id, rating FROM restaurants WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;

            int t_attraction_id = resultSet.getInt(2);
            TouristAttraction touristAttraction = touristAttractionDao.get(t_attraction_id);

            String name = resultSet.getString(1);
            float rating = resultSet.getFloat(2);

            Restaurant restaurant = new Restaurant(name, rating, touristAttraction);
            restaurant.setId(id);

            return restaurant;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting a Restaurant");
        }
    }

    @Override
    public List<Restaurant> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, name, t_attraction_id, rating FROM restaurants";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            List<Restaurant> result = new ArrayList<>();
            while (resultSet.next()) {
                int t_attraction_id = resultSet.getInt(3);
                TouristAttraction touristAttraction = touristAttractionDao.get(t_attraction_id);

                String name = resultSet.getString(2);
                float rating = resultSet.getFloat(4);

                Restaurant restaurant = new Restaurant(name, rating, touristAttraction);
                restaurant.setId(resultSet.getInt(1));

                result.add(restaurant);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting a Restaurant");
        }
    }

    @Override
    public List<Restaurant> getAllByTouristAttraction(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT name, t_attraction_id, rating FROM restaurants WHERE t_attraction_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            List<Restaurant> result = new ArrayList<>();
            while (resultSet.next()) {
                TouristAttraction touristAttraction = touristAttractionDao.get(id);

                String name = resultSet.getString(1);
                float rating = resultSet.getFloat(2);

                Restaurant restaurant = new Restaurant(name, rating, touristAttraction);
                restaurant.setId(resultSet.getInt(1));

                result.add(restaurant);

            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting a Restaurant");
        }
    }
}
