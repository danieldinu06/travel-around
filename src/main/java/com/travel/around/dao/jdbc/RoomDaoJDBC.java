package com.travel.around.dao.jdbc;

import com.travel.around.dao.HotelDao;
import com.travel.around.dao.RoomDao;
import com.travel.around.model.Hotel;
import com.travel.around.model.Room;

import javax.sql.DataSource;
import java.util.List;

public class RoomDaoJDBC implements RoomDao {
    private final DataSource dataSource;
    private HotelDao hotelDao;

    public RoomDaoJDBC(DataSource dataSource, HotelDao hotelDao) {
        this.dataSource = dataSource;
        this.hotelDao = hotelDao;
    }

    @Override
    public void add(Room room) {

    }

    @Override
    public Room get(int id) {
        return null;
    }

    @Override
    public List<Room> getAllByHotel(Hotel hotel) {
        return null;
    }

    @Override
    public List<Room> getAll() {
        return null;
    }
}
