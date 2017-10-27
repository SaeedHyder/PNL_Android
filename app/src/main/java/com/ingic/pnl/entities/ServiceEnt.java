package com.ingic.pnl.entities;

/**
 * Created on 10/19/2017.
 */

public class ServiceEnt {
    private int ImageResID;
    private String text;

    public ServiceEnt(int imageResID, String text) {
        ImageResID = imageResID;
        this.text = text;
    }


    public int getImageResID() {
        return ImageResID;
    }

    public void setImageResID(int imageResID) {
        ImageResID = imageResID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
