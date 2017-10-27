package com.ingic.pnl.entities;

/**
 * Created by saeedhyder on 10/19/2017.
 */

public class ReviewsEnt {

    String title;
    String description;
    float rating;

    public ReviewsEnt(String title, String description, float rating) {
        this.title = title;
        this.description = description;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
