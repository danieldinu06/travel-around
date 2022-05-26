package dao.jdbc;

import dao.RestaurantDao;
import model.Restaurant;
import model.TouristAttraction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class RestaurantDaoJDBC implements RestaurantDao {

    private final DataSource dataSource;

    public RestaurantDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
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
    public Restaurant get(String name) {
        return null;
    }

    @Override
    public List<Restaurant> getAll() {
        return null;
    }

    @Override
    public List<Restaurant> getAllByTouristAttraction(Integer id) {
        return null;
    }
}
