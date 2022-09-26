package com.travel.around.dao;

import com.travel.around.model.Hotel;

import java.util.List;

public interface HotelDao {
    void add(Hotel hotel);
    Hotel get(Integer id);
    List<Hotel> getHotelsByTouristAttraction(Integer touristAttractionId);
}
