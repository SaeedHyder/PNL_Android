package com.ingic.pnl.entities;

/**
 * Created by saeedhyder on 10/20/2017.
 */

public class servicesGridViewEnt {

    private String image;
    private String text;
    private Integer imageint;

    public servicesGridViewEnt(String image, String text, Integer imageint) {
        this.image = image;
        this.text = text;
        this.imageint = imageint;
    }
    public servicesGridViewEnt(String image, String text) {
        this.image = image;
        this.text = text;
    }

    public Integer getImageint() {
        return imageint;
    }

    public void setImageint(Integer imageint) {
        this.imageint = imageint;
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
