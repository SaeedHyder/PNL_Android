package com.ingic.pnl.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by saeedhyder on 10/31/2017.
 */

public class CompanyDetailEnt {

    @SerializedName("companyModel")
    @Expose
    private CompanyModel companyModel;
    @SerializedName("reviewModel")
    @Expose
    private ReviewModel reviewModel;

    public CompanyModel getCompanyModel() {
        return companyModel;
    }

    public void setCompanyModel(CompanyModel companyModel) {
        this.companyModel = companyModel;
    }

    public ReviewModel getReviewModel() {
        return reviewModel;
    }

    public void setReviewModel(ReviewModel reviewModel) {
        this.reviewModel = reviewModel;
    }
}
