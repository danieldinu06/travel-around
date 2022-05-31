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
            String sql = "INSERT INTO tourist_attractions (name, description, rating) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, touristAttraction.getName());
            statement.setString(2, touristAttraction.getDescription());
            statement.setFloat(3, touristAttraction.getRating());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            touristAttraction.setId(resultSet.getInt(1));

        }
        catch (SQLException e) {
            throw new RuntimeException("Error while adding new Tourist Attractions");
        }
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public TouristAttraction get(Integer touristAttractionId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT name, description, rating FROM tourist_attractions WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, touristAttractionId);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) return null;

            String name = resultSet.getString(1);
            String description = resultSet.getString(2);
            float rating = resultSet.getFloat(3);

            TouristAttraction touristAttraction = new TouristAttraction(name, description, rating);

            touristAttraction.setId(touristAttractionId);

            return touristAttraction;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting TouristAttraction.");
        }
    }

    @Override
    public List<TouristAttraction> getAll() {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT id, name, description, rating FROM tourist_attractions;";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);

            List<TouristAttraction> result = new ArrayList<>();
            while(resultSet.next()) {
                String name = resultSet.getString(2);
                String description = resultSet.getString(3);
                float rating = resultSet.getFloat(4);

                TouristAttraction touristAttraction = new TouristAttraction(name, description, rating);
                touristAttraction.setId(resultSet.getInt(1));

                result.add(touristAttraction);
            }

            return result;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting TouristAttraction.");
        }
    }



}
