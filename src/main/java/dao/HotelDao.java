package dao;

import model.Hotel;

import java.util.List;

public interface HotelDao {
    void add(Hotel hotel);
    Hotel get(String name);
    List<Hotel> getHotelsByTouristAttraction();
}
