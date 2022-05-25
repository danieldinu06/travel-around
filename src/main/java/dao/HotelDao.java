package dao;

import model.Hotel;

import java.util.List;

public interface HotelDao {
    void addHotel(Hotel hotel, TouristAttraction touristAttraction);
    List<Hotel> getAll(TouristAttraction touristAttraction);
}
