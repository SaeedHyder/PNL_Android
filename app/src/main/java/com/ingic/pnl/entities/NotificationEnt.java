package com.ingic.pnl.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saeedhyder on 10/19/2017.
 */

public class NotificationEnt {


    @SerializedName("UnReadCount")
    private int UnReadCount;
    @SerializedName("Notifications")
    private ArrayList<NotificationItemEnt> Notifications;

    public int getUnReadCount() {
        return UnReadCount;
    }

    public void setUnReadCount(int UnReadCount) {
        this.UnReadCount = UnReadCount;
    }

    public ArrayList<NotificationItemEnt> getNotifications() {
        return Notifications;
    }

    public void setNotifications(ArrayList<NotificationItemEnt> Notifications) {
        this.Notifications = Notifications;
    }


}
