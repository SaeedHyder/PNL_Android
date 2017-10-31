package com.ingic.pnl.retrofit;


import com.ingic.pnl.entities.CompanyDetailEnt;
import com.ingic.pnl.entities.CompanyModel;
import com.ingic.pnl.entities.FavoritesEnt;
import com.ingic.pnl.entities.ResponseWrapper;
import com.ingic.pnl.entities.ReviewsEnt;
import com.ingic.pnl.entities.SortingByEnt;
import com.ingic.pnl.entities.UserIDEnt;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebService {
    @FormUrlEncoded
    @POST("account/signin")
    Call<ResponseWrapper<UserIDEnt>> makeUserLogin(@Field("email") String Email, @Field("password") String Password);

    @FormUrlEncoded
    @POST("account/signup")
    Call<ResponseWrapper<UserIDEnt>> makeUserSignup(@Field("name") String Name, @Field("email") String Email,
                                                    @Field("password") String Password,
                                                    @Field("ConfirmPassword") String ConfirmPassword);


    @FormUrlEncoded
    @POST("account/updatepassword")
    Call<ResponseWrapper> changePassword(@Field("UserId") String UserId,
                                         @Field("OldPassword") String OldPassword,
                                         @Field("Password") String Password,
                                         @Field("ConfirmPassword") String ConfirmPassword);

    @FormUrlEncoded
    @POST("account/forgotpassword")
    Call<ResponseWrapper> forgotPassword(@Query("Email") String Email);

   /* @GET("/api/company/GetAllMyFavorites")
    Call<ResponseWrapper> traineeProfile(
            @Path("userId") int userId);*/

    @GET("company/GetAllStartsWith/{c}")
    Call<ResponseWrapper<ArrayList<SortingByEnt>>> getCompaniesByCaracter(@Path("c") String caracter);

    @GET("company/get/{id}")
    Call<ResponseWrapper<CompanyDetailEnt>> getCompanyDetail(@Path("id") int id);

    @FormUrlEncoded
    @POST("company/MarkFavorite")
    Call<ResponseWrapper> markFavorite(@Field("UserId") String UserId,
                                       @Field("CompanyId") int  CompanyId,
                                       @Field("IsMarkedFavorite") boolean IsMarkedFavorite);

    @GET("company/GetAllMyFavorites/{id}")
    Call<ResponseWrapper<ArrayList<FavoritesEnt>>> getFavouriteList(@Path("id") String id);

    @GET("review/GetAllByCompany/{id}")
    Call<ResponseWrapper<ArrayList<ReviewsEnt>>> getReviewList(@Path("id") int id);

    @FormUrlEncoded
    @POST("review/CreateReview")
    Call<ResponseWrapper> createReview(@Field("UserId") String UserId,
                                       @Field("CompanyId") int  CompanyId,
                                       @Field("Points") int  Points,
                                       @Field("Analysis") String  Analysis,
                                       @Field("IsAnonymous") boolean IsAnonymous);

}