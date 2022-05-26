package dao;

import model.Hotel;

import java.util.List;

public interface HotelDao {
    void addHotel(Hotel hotel);
    Hotel getHotel(String name);
    List<Hotel> getHotelsByTouristAttraction();
}
