package dao.implementation;

import model.Hotel;
import model.Restaurant;
import model.TouristAttraction;

import java.util.List;

public interface TouristAttractionDao {
    void addTouristAttraction(TouristAttraction touristAttraction);
    void removeTouristAttraction(TouristAttraction touristAttraction);
    List<TouristAttraction> getAllTouristAttractions();
}
