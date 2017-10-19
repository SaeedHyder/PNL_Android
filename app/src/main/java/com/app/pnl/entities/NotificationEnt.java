package com.app.pnl.entities;

/**
 * Created by saeedhyder on 10/19/2017.
 */

public class NotificationEnt {

    String title;
    String description;
    String time;

    public NotificationEnt(String title, String description, String time) {
        this.title = title;
        this.description = description;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
