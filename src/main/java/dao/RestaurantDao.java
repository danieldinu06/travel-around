package dao;

import model.Restaurant;

import java.util.List;

public interface RestaurantDao {
    void add(Restaurant restaurant);
    Restaurant get(String name);
    List<Restaurant> getAll();
    List<Restaurant> getAllByTouristAttraction(Integer id);
}
