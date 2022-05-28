package model;

public class Restaurant {
    private Integer id;
    private final String name;
    private float rating;
    private final TouristAttraction touristAttraction;

    public Restaurant(String name, float rating, TouristAttraction touristAttraction) {
        this.name = name;
        this.rating = rating;
        this.touristAttraction = touristAttraction;
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
