package model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private Integer id;

    private String name;

    private String description;

    private float rating;
    private List<String> images;

    private List<Hotel> hotels;

    private List<Restaurant> restaurants;

    public TouristAttraction(String name, String description, float rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.hotels = new ArrayList<>();
        this.restaurants = new ArrayList<>();
    }

    public List<String> getImages() {
        return images;
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

    public void setName(String name) {
        this.name = name;
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

    public List<Hotel> getHotels() {
        return hotels;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
}
