package dao;

import model.Hotel;
import model.Room;

import java.util.List;

public interface RoomDao {
    void add(Room room);
    Room get(int id);
    List<Room> getAllByHotel(Hotel hotel);
    List<Room> getAll();
}
