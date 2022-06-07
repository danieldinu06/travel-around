package dao.jdbc;

import dao.UserDao;
import model.User;
import model.utils.UserStatus;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoJDBC implements UserDao {
    private final DataSource dataSource;

    public UserDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO users (name, password, phone_number, billing_address, user_status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getBillingAddress());
            statement.setString(5, user.getStatus().toString());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            user.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException("Error while adding new User.");
        }
    }

    @Override
    public void remove(User user) {

    }

    @Override
    public User get(String name) {
        try (Connection connection = dataSource.getConnection()) {

            String sql = "SELECT id, name, password, phone_number, billing_address, user_status FROM users WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            System.out.println(resultSet);

            if(!resultSet.next()) return null;

            String password = resultSet.getString(3);
            String phoneNumber = resultSet.getString(4);
            String billingAddress = resultSet.getString(5);
            UserStatus userStatus = UserStatus.valueOf(resultSet.getString(6));

            User user = new User(name, password, phoneNumber, billingAddress, userStatus);
            user.setId(resultSet.getInt(1));

            return user;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting User.");
        }
    }
}
