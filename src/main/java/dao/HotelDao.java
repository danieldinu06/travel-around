package dao;

import model.Hotel;
import model.TouristAttraction;

import java.util.List;

public interface HotelDao {
    void add(Hotel hotel);
    Hotel get(Integer id);
    List<Hotel> getHotelsByTouristAttraction(Integer touristAttractionId);
}
