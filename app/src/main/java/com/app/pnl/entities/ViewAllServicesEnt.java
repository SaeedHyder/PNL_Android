package com.app.pnl.entities;

/**
 * Created by saeedhyder on 10/19/2017.
 */

public class ViewAllServicesEnt {

    String companiesSorting;

    public ViewAllServicesEnt(String companiesSorting) {
        this.companiesSorting = companiesSorting;
    }

    public String getCompaniesSorting() {
        return companiesSorting;
    }

    public void setCompaniesSorting(String companiesSorting) {
        this.companiesSorting = companiesSorting;
    }
}
