package dao;

import model.Restaurant;

import java.util.List;

public interface RestaurantDao {
    void add(Restaurant restaurant);
    Restaurant get(Integer id);
    List<Restaurant> getAll();
    List<Restaurant> getAllByTouristAttraction(Integer id);
}
