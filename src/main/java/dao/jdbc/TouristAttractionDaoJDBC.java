package dao.jdbc;

import dao.TouristAttractionDao;
import model.TouristAttraction;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TouristAttractionDaoJDBC implements TouristAttractionDao {

    private final DataSource dataSource;

    public TouristAttractionDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(TouristAttraction touristAttraction) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO tourist_attractions (name, description, rating, url) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, touristAttraction.getName());
            statement.setString(2, touristAttraction.getDescription());
            statement.setFloat(3, touristAttraction.getRating());
            statement.setString(4, touristAttraction.getUrl());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            touristAttraction.setId(resultSet.getInt(1));

            addImages(touristAttraction.getId(), touristAttraction.getImages());
        }
        catch (SQLException e) {
            throw new RuntimeException("Error while adding new Tourist Attractions");
        }
    }

    public void addImages(Integer touristAttractionId, List<String> images) {
        try (Connection connection = dataSource.getConnection()) {
            for (String image: images) {
                String sql = "INSERT INTO tourist_attraction_images (image) VALUES(?)";
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                statement.setString(1, image);
                statement.executeUpdate();

                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();

                int firstIndex = resultSet.getInt(1);

                connectImages(touristAttractionId, firstIndex);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting TouristAttraction Images.");
        }
    }

    public void connectImages(Integer touristAttractionId, int index) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO ta_i_seq (t_attraction_id, ta_image_id) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(1, touristAttractionId);
            statement.setInt(2, index);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while getting TouristAttraction Images.");
        }
    }

    @Override
    public void remove(Integer touristAttractionId) {

    }

//    @Override
//    public void remove(int id) {
//
//        String sql = "DELETE FROM product WHERE id ='" + id + "';";
//        dataSource.executeQuery(sql);
//    }
//
//    private List<TouristAttraction> get(String query) {
//        List<TouristAttraction> resultList = new ArrayList<>();
//
//        try (Connection connection = dataSource.getConnection();
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(query);
//
//        ) {
//            TouristAttractionDao productCategoryDataStore = TouristAttractionDaoJDBC.get();
//
//
//            while (resultSet.next()) {
//
//                TouristAttraction product = new TouristAttraction(
//                        resultSet.getString("name"),
//                        resultSet.getString("description"),
//                        resultSet.getString("description"),
//                        resultSet.getString("image"),
//                        resultSet.getFloat("rating");
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return resultList;
//    }
//

    @Override
    public TouristAttraction get(Integer touristAttractionId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, name, description, rating, url FROM tourist_attractions WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, touristAttractionId);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) return null;

            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            float rating = resultSet.getFloat(4);
            String url = resultSet.getString(5);

            List<String> images = getImages(resultSet.getInt(1));
            TouristAttraction touristAttraction = new TouristAttraction(name, description, rating);
            touristAttraction.setUrl(url);
            touristAttraction.setId(touristAttractionId);

            return touristAttraction;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting TouristAttraction.");
        }
    }

    @Override
    public List<TouristAttraction> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, name, description, rating, url FROM tourist_attractions;";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            List<TouristAttraction> result = new ArrayList<>();
            while(resultSet.next()) {
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                float rating = resultSet.getFloat(4);
                String url = resultSet.getString(5);
                List<String> images = getImages(resultSet.getInt(1));

                TouristAttraction touristAttraction = new TouristAttraction(name, description, rating);
                touristAttraction.setUrl(url);
                touristAttraction.setId(resultSet.getInt(1));
                touristAttraction.setImages(images);

                result.add(touristAttraction);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting TouristAttraction.");
        }
    }

    public List<String> getImages(Integer touristAttractionId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT image FROM tourist_attraction_images\n" +
                    "INNER JOIN ta_i_seq tis on tourist_attraction_images.id = tis.ta_image_id\n" +
                    "LEFT OUTER JOIN tourist_attractions ta on ta.id = tis.t_attraction_id\n" +
                    "WHERE ta.id = ?;";
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
            throw new RuntimeException("Error while getting TouristAttraction Images.");
        }
    }

}
