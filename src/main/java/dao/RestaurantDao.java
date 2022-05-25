package dao;

import model.Restaurant;
import model.TouristAttraction;

public interface RestaurantDao {
    void addRestaurant(Restaurant restaurant);
    void removeRestaurant(Restaurant restaurant);
    void getAllRestaurant(TouristAttraction touristAttraction);
}
