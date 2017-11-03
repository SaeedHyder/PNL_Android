package com.ingic.pnl.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 10/27/2017.
 */

public class UserIDEnt {

    @SerializedName("UserId")
    private int UserId;

    @SerializedName("AuthToken")
    private String AuthToken;



    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getAuthToken() {
        return AuthToken;
    }

    public void setAuthToken(String authToken) {
        AuthToken = authToken;
    }
}
