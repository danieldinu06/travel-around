package dao.jdbc;

import dao.TouristAttractionDao;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class TouristAttractionDaoJDBC implements TouristAttractionDao {

    private DataSource dataSource;

    public void TouristAttractionJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addTouristAttraction(model.TouristAttraction touristAttraction) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO tourist_attractions (name, image, description, rating) VALUES(?, ?, ?, ?)";
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

    @Override
    public void removeTouristAttraction(model.TouristAttraction touristAttraction) {

    }

    @Override
    public List<model.TouristAttraction> getAllTouristAttractions() {
        return null;
    }



}
