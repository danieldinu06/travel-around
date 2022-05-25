package dao;

import model.Hotel;
import model.TouristAttraction;

import java.util.List;

public interface TouristAttractionDao {
    void addTouristAttraction(TouristAttraction touristAttraction);
    List<TouristAttraction> getAll();

    void removeTouristAttraction(TouristAttraction touristAttraction);




}
