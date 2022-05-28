package dao;

import model.Hotel;
import model.Restaurant;
import model.TouristAttraction;

import java.util.List;

public interface TouristAttractionDao {
    void add(TouristAttraction touristAttraction);
    void remove(TouristAttraction touristAttraction);
    TouristAttraction get(Integer touristAttractionId);
    List<TouristAttraction> getAll();
}
