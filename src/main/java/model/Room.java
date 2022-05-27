package model;

public class Room {
    private final int beds;
    private final int floor;
    private float price;
    private final Hotel hotel;

    public Room(int beds, int floor, float price, Hotel hotel) {
        this.beds = beds;
        this.floor = floor;
        this.price = price;
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return hotel;
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
