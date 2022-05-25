package model;

public class Room {
    private final int beds;
    private final int floor;
    private float price;

    public Room(int beds, int floor, float price) {
        this.beds = beds;
        this.floor = floor;
        this.price = price;
    }

    public int getBeds() {
        return beds;
    }

    public int getFloor() {
        return floor;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
