package dao.jdbc;

import dao.HotelDao;
import dao.RoomDao;
import model.Hotel;
import model.Room;

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
