package model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private Integer id;
    private final String name;
    private float rating;
    private List<String> images;
    private final TouristAttraction touristAttraction;

    public Restaurant(String name, float rating, TouristAttraction touristAttraction) {
        this.name = name;
        this.rating = rating;
        this.touristAttraction = touristAttraction;
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

    public TouristAttraction getTouristAttraction() {
        return touristAttraction;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
