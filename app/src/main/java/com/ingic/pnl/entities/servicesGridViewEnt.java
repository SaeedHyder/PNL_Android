package com.ingic.pnl.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by saeedhyder on 10/20/2017.
 */

public class servicesGridViewEnt {

    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
