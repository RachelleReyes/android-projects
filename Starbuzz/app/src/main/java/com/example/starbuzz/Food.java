package com.example.starbuzz;

public class Food {
    private String name;
    private String description;
    private int imageResourceId;

    //drinks is an array of Food
    public static final Food[] food = {
            new Food("Muffin", "Delicious chocolate muffin with chocolate chips",
                    R.drawable.muffin),
            new Food("Sandwich", "Breakfast sandwich with chicken and vegetable",
                    R.drawable.sandwich),
            new Food("Cookies", "Chewy chocolate chip cookies",
                    R.drawable.cookies)};

    //Each Drink has a name, description, and an image resource
    private Food(String name, String description, int imageResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String toString() {
        return this.name;
    }

}