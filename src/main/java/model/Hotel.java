package model;

import java.util.Arrays;

public class Hotel {
    private String name;
    private String image;
    private String description;
    private float rating;
    private int rooms_number;

    private Room[][] rooms;

    public Hotel(String name, String image, String description, float rating, int rooms_number, Room[][] rooms) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.rating = rating;
        this.rooms_number = rooms_number;
        this.rooms = rooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Room[][] getRooms() {
        return rooms;
    }

    public void setRooms(Room[][] rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", rooms_number=" + rooms_number +
                ", rooms=" + Arrays.toString(rooms) +
                '}';
    }
}
