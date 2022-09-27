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
            String sql = "INSERT INTO users (name, email, password, phone_number, billing_address, user_status) VALUES (?, ?, ?, ?, ?, ?::USER_STATUS)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhoneNumber());
            statement.setString(5, user.getBillingAddress());
            statement.setString(6, user.getStatus().toString());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            user.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            throw new RuntimeException("Error while adding new User.");
        }
    }

    @Override
    public void updateUserStatus(User user) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "UPDATE users SET user_status = ?::USER_STATUS WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, user.getStatus().toString());
            statement.setString(2, user.getName());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error while updating User.");
        }
    }

    @Override
    public void remove(User user) {

    }

    @Override
    public User get(String name) {
        try (Connection connection = dataSource.getConnection()) {

            String sql = "SELECT id, name, email, password, phone_number, billing_address, user_status FROM users WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if(!resultSet.next()) return null;

            String email = resultSet.getString(3);
            String password = resultSet.getString(4);
            String phoneNumber = resultSet.getString(5);
            String billingAddress = resultSet.getString(6);
            UserStatus userStatus = UserStatus.valueOf(resultSet.getString(7));

            User user = new User(name, email, password, phoneNumber, billingAddress, userStatus);
            user.setId(resultSet.getInt(1));

            return user;

        } catch (SQLException e) {
            throw new RuntimeException("Error while getting User.");
        }
    }
}
