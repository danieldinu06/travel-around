package dao;

import model.User;

public interface UserDao {
    void add(User user);
    void remove(User user);
    User get(String name);
}
