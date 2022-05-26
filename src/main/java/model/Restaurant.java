package model;

public class Restaurant {
    private Integer id;
    private final String name;
    private float rating;
    private TouristAttraction touristAttraction;

    public Restaurant(String name, float rating) {
        this.name = name;
        this.rating = rating;
    }

    public TouristAttraction getTouristAttraction() {
        return touristAttraction;
    }

    public void setTouristAttraction(TouristAttraction touristAttraction) {
        this.touristAttraction = touristAttraction;
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
