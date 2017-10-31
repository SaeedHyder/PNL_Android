package com.ingic.pnl.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by saeedhyder on 10/19/2017.
 */

public class SortingByEnt {


    @SerializedName("Name")
    private String Name;
    @SerializedName("Description")
    private String Description;
    @SerializedName("ImageUrl")
    private String ImageUrl;
    @SerializedName("WebUrl")
    private String WebUrl;
    @SerializedName("LocationUrl")
    private String LocationUrl;
    @SerializedName("Id")
    private int Id;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String ImageUrl) {
        this.ImageUrl = ImageUrl;
    }

    public String getWebUrl() {
        return WebUrl;
    }

    public void setWebUrl(String WebUrl) {
        this.WebUrl = WebUrl;
    }

    public String getLocationUrl() {
        return LocationUrl;
    }

    public void setLocationUrl(String LocationUrl) {
        this.LocationUrl = LocationUrl;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
}
