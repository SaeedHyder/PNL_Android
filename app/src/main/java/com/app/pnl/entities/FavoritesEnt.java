package com.app.pnl.entities;

/**
 * Created by saeedhyder on 10/19/2017.
 */

public class FavoritesEnt {

    String image;
    String companyName;
    String description;
    String location;
    String phone;

    public FavoritesEnt(String image, String companyName, String description, String location, String phone) {
        this.image = image;
        this.companyName = companyName;
        this.description = description;
        this.location = location;
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
