package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hotel {
    private Integer id;

    private final String name;

    private final String image;

    private String description;
    private float rating;

    private int rooms_number;

    private List<Room> rooms;

    private TouristAttraction touristAttraction;

    public Hotel(String name, String image, float rating) {
        this.name = name;
        this.image = image;
        this.rating = rating;
        this.rooms = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRooms_number() {
        return rooms_number;
    }

    public void setRooms_number(int rooms_number) {
        this.rooms_number = rooms_number;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public TouristAttraction getTouristAttraction() {
        return touristAttraction;
    }

    public void setTouristAttraction(TouristAttraction touristAttraction) {
        this.touristAttraction = touristAttraction;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", rooms_number=" + rooms_number +
                '}';
    }
}




