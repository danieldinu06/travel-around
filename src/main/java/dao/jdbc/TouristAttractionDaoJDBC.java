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
            String sql = "INSERT INTO tourist_attractions (name, image, description, rating) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, touristAttraction.getName());
            statement.setString(2, touristAttraction.getImage());
            statement.setString(3, touristAttraction.getDescription());
            statement.setFloat(4, touristAttraction.getRating());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            touristAttraction.setId(resultSet.getInt(1));

        }
        catch (SQLException e) {
            throw new RuntimeException("Error while adding new Tourist Attractions");
        }
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
            String sql = "SELECT name, image, description, rating FROM tourist_attractions WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, touristAttractionId);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) return null;

            String name = resultSet.getString(1);
            String image = resultSet.getString(2);
            String description = resultSet.getString(3);
            float rating = resultSet.getFloat(4);

            TouristAttraction touristAttraction = new TouristAttraction(name, image, description, rating);

            touristAttraction.setId(touristAttractionId);

            return touristAttraction;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting TouristAttraction.");
        }
    }

    @Override
    public List<TouristAttraction> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, name, image, description, rating FROM tourist_attractions;";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            List<TouristAttraction> result = new ArrayList<>();
            while(resultSet.next()) {
                String name = resultSet.getString(2);
                String image = resultSet.getString(3);
                String description = resultSet.getString(4);
                float rating = resultSet.getFloat(5);

                TouristAttraction touristAttraction = new TouristAttraction(name, image, description, rating);
                touristAttraction.setId(resultSet.getInt(1));

                result.add(touristAttraction);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting TouristAttraction.");
        }
    }



}
