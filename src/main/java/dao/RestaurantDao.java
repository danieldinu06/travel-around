package dao.implementation;

import model.Restaurant;

import java.util.List;

public interface RestaurantDao {
    void addRestaurant(Restaurant restaurant);
    Restaurant getRestaurant(String name);
    List<Restaurant> getRestaurants();
}
