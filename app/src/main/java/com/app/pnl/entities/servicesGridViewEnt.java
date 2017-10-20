package com.app.pnl.entities;

/**
 * Created by saeedhyder on 10/20/2017.
 */

public class servicesGridViewEnt {

    private String image;
    private String text;


    public servicesGridViewEnt(String image, String text) {
        this.image = image;
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
