package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TouristAttraction {
    private Integer id;
    private String name;
    private String description;
    private float rating;
    private String url;
    private String location;
    private Map<String, String> schedule;
    private List<String> images;
    private List<Hotel> hotels;
    private List<Restaurant> restaurants;

    public TouristAttraction(String name, String description, float rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.hotels = new ArrayList<>();
        this.restaurants = new ArrayList<>();
        this.schedule = new HashMap<>();
    }

    public Map<String, String> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<String, String> schedule) {
        this.schedule = schedule;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public void addHotel(Hotel hotel) {
        this.hotels.add(hotel);
    }

    public void addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
    }
}
