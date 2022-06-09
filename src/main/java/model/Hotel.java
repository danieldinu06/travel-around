package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hotel {
    private Integer id;

    private final String name;

    private String description;
    private float rating;

    private int rooms_number;

    private List<Room> rooms;
    private List<String> images;

    private TouristAttraction touristAttraction;

    public Hotel(String name, String description, float rating, int rooms_number, TouristAttraction touristAttraction) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.rooms_number = rooms_number;
        this.touristAttraction = touristAttraction;
        this.rooms = new ArrayList<>();
        this.images = new ArrayList<>();
    }

    public String getFirstImage() {
        return this.images.get(0);
    }

    public List<String> getImages() {
        return images;
    }

    public void addImage(String image) {
        this.images.add(image);
    }

    public void setImages(List<String> images) {
        this.images = images;
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
}




