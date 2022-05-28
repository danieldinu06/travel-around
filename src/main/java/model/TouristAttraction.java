package model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private Integer id;

    private String name;

    private String image;

    private String description;

    private float rating;

    private List<Hotel> hotels;

    private List<Restaurant> restaurants;

    public TouristAttraction(String name, String image, String description, float rating) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.rating = rating;
        this.hotels = new ArrayList<>();
        this.restaurants = new ArrayList<>();
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

    public List<Hotel> getHotels() {
        return hotels;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    @Override
    public String toString() {
        return "TouristAttraction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", hotels=" + hotels +
                ", restaurants=" + restaurants +
                '}';
    }
}
