package com.ingic.pnl.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created on 10/27/2017.
 */

public class UserIDEnt {

    @SerializedName("UserId")
    private int UserId;

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }
}
