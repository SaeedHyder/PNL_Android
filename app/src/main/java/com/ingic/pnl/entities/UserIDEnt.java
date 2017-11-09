package com.ingic.pnl.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created on 10/27/2017.
 */

public class UserIDEnt {

    @SerializedName("User")
    @Expose
    private User user;

    @SerializedName("AuthToken")
    private String AuthToken;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthToken() {
        return AuthToken;
    }

    public void setAuthToken(String authToken) {
        AuthToken = authToken;
    }
}
