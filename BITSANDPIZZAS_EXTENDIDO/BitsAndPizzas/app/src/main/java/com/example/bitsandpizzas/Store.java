package com.example.bitsandpizzas;

public class Store {
    private String name;
    private int imageResourceId;

    public static final Store[] stores = {
            new Store("Berlin", R.drawable.dublin),
            new Store("Australia", R.drawable.australia)
    };

    private Store(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
