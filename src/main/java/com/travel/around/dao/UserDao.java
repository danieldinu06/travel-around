package com.travel.around.dao;

import com.travel.around.model.User;

public interface UserDao {
    void add(User user);
    void remove(User user);
    void updateUserStatus(User user);
    User get(String name);
}
