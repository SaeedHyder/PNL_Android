package com.ingic.pnl.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by saeedhyder on 10/19/2017.
 */

public class ReviewsEnt {

    @SerializedName("CompanyId")
    @Expose
    private Integer companyId;
    @SerializedName("UserName")
    @Expose
    private Object userName;
    @SerializedName("Analysis")
    @Expose
    private String analysis;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
