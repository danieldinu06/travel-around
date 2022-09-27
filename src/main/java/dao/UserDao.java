package dao;

import model.User;
import model.utils.UserStatus;

public interface UserDao {
    void add(User user);
    void remove(User user);
    void updateUserStatus(User user);
    User get(String name);
}
