package com.travel.around.dao.jdbc;

import com.travel.around.dao.RestaurantDao;
import com.travel.around.dao.TouristAttractionDao;
import com.travel.around.model.Restaurant;
import com.travel.around.model.TouristAttraction;

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
            String sql = "INSERT INTO restaurants (name, description, t_attraction_id, rating, location) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, restaurant.getName());
            statement.setString(2, restaurant.getDescription());
            statement.setInt(3, restaurant.getTouristAttraction().getId());
            statement.setFloat(4, restaurant.getRating());
            statement.setString(5, restaurant.getLocation());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            restaurant.setId(resultSet.getInt(1));

            addImages(restaurant.getId(), restaurant.getImages());

        } catch (SQLException e) {
            throw new RuntimeException("Error while adding new Restaurant.");
        }
    }

    public void addImages(Integer restaurantId, List<String> images) {
        try (Connection connection = dataSource.getConnection()) {
            for (String image: images) {
                String sql = "INSERT INTO restaurant_images (image) VALUES(?)";
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, image);
                statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();

                int firstIndex = resultSet.getInt(1);

                connectImages(restaurantId, firstIndex);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting Hotel Images.");
        }
    }

    public void connectImages(Integer restaurantId, int index) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO r_i_seq (restaurant_id, restaurant_image_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, restaurantId);
            statement.setInt(2, index);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting Hotel Images.");
        }
    }

    public List<String> getImages(Integer restaurantId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT image FROM restaurant_images\n" +
                    "INNER JOIN r_i_seq ris on restaurant_images.id = ris.restaurant_image_id\n" +
                    "LEFT OUTER JOIN restaurants r on ris.restaurant_id = r.id\n" +
                    "WHERE r.id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();

            List<String> result = new ArrayList<>();
            while(resultSet.next()) {
                String image = resultSet.getString(1);
                result.add(image);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting TouristAttraction Images.");
        }
    }

    @Override
    public Restaurant get(Integer id) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT name, description, t_attraction_id, rating, location FROM restaurants WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) return null;

            int t_attraction_id = resultSet.getInt(3);
            TouristAttraction touristAttraction = touristAttractionDao.get(t_attraction_id);

            String name = resultSet.getString(1);
            String description = resultSet.getString(2);
            float rating = resultSet.getFloat(4);
            String location = resultSet.getString(5);

            List<String> images = getImages(id);

            Restaurant restaurant = new Restaurant(name, description, rating, touristAttraction);
            restaurant.setId(id);
            restaurant.setImages(images);
            restaurant.setLocation(location);

            return restaurant;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting a Restaurant");
        }
    }

    @Override
    public List<Restaurant> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, name, description, t_attraction_id, rating, location FROM restaurants";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            List<Restaurant> result = new ArrayList<>();
            while (resultSet.next()) {
                int t_attraction_id = resultSet.getInt(4);
                TouristAttraction touristAttraction = touristAttractionDao.get(t_attraction_id);

                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                float rating = resultSet.getFloat(4);
                String location = resultSet.getString(5);

                List<String> images = getImages(resultSet.getInt(1));

                Restaurant restaurant = new Restaurant(name, description, rating, touristAttraction);
                restaurant.setId(resultSet.getInt(1));
                restaurant.setImages(images);
                restaurant.setLocation(location);

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
            String sql = "SELECT id, name, description, rating, location FROM restaurants WHERE t_attraction_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            List<Restaurant> result = new ArrayList<>();
            while (resultSet.next()) {
                TouristAttraction touristAttraction = touristAttractionDao.get(id);

                List<String> images = getImages(resultSet.getInt(1));
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                float rating = resultSet.getFloat(4);
                String location = resultSet.getString(5);

                Restaurant restaurant = new Restaurant(name, description, rating, touristAttraction);
                restaurant.setId(resultSet.getInt(1));
                restaurant.setImages(images);
                restaurant.setLocation(location);

                result.add(restaurant);

            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting a Restaurant");
        }
    }
}
