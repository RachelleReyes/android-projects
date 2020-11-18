package com.example.starbuzz;

public class Store {
    private String name;
    private String address;
    private String hours;
    private int imageResourceId;

    //drinks is an array of Store
    public static final Store[] store = {
            new Store("Chula Vista", "555 Broadway # 147 Chula Vista, CA 91910", "Monday to Sunday, 5:30 AM to 9:00 PM",
                    R.drawable.chulavista),
            new Store("San Ysidro", "4201 Camino De La Plaza #102, San Ysidro, CA 92173, USA","Monday to Sunday, 4:30 AM to 10:00 PM",
                    R.drawable.sanysidro),
            new Store("National City", "404 Mile of Cars Way 101 National City, CA 91950","Monday to Sunday, 4:00 AM to 11:00 PM",
                    R.drawable.nationalcity)};

    //Each Drink has a name, description, and an image resource
    private Store(String name, String address,String hours, int imageResourceId) {
        this.name = name;
        this.address = address;
        this.hours = hours;
        this.imageResourceId = imageResourceId;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getHours() {
        return hours;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String toString() {
        return this.name;
    }

}