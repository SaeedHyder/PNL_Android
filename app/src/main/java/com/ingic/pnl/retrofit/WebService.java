package com.ingic.pnl.retrofit;


import com.ingic.pnl.entities.CompanyDetailEnt;
import com.ingic.pnl.entities.FavoritesEnt;
import com.ingic.pnl.entities.PopularEnt;
import com.ingic.pnl.entities.ResponseWrapper;
import com.ingic.pnl.entities.ReviewsEnt;
import com.ingic.pnl.entities.ServiceEnt;
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
    @POST("account/signinwithsocialmedia")
    Call<UserIDEnt> makeUserSocialMediaLogin(@Field("AuthToken") String authToken);

    @FormUrlEncoded
    @POST("account/signup")
    Call<ResponseWrapper<UserIDEnt>> makeUserSignup(@Field("name") String Name, @Field("email") String Email,
                                                    @Field("password") String Password,
                                                    @Field("ConfirmPassword") String ConfirmPassword,
                                                    @Field("FacebookAuthToken") String facebookToken,
                                                    @Field("GoogleAuthToken") String googleToken);


    @FormUrlEncoded
    @POST("account/updatepassword")
    Call<ResponseWrapper> changePassword(@Field("UserId") String UserId,
                                         @Field("OldPassword") String OldPassword,
                                         @Field("Password") String Password,
                                         @Field("ConfirmPassword") String ConfirmPassword);


    @GET("account/forgotpassword")
    Call<ResponseWrapper<String>> forgotPassword(@Query("email") String email);

    @GET("company/GetAllStartsWith/{c}")
    Call<ResponseWrapper<ArrayList<SortingByEnt>>> getCompaniesByCaracter(@Path("c") String caracter);

    @GET("company/get/{id}")
    Call<ResponseWrapper<CompanyDetailEnt>> getCompanyDetail(@Path("id") int id);

    @FormUrlEncoded
    @POST("company/MarkFavorite")
    Call<ResponseWrapper> markFavorite(@Field("UserId") String UserId,
                                       @Field("CompanyId") int CompanyId,
                                       @Field("IsMarkedFavorite") boolean IsMarkedFavorite);

    @GET("company/GetAllMyFavorites/{id}")
    Call<ResponseWrapper<ArrayList<FavoritesEnt>>> getFavouriteList(@Path("id") String id);

    @GET("review/GetAllByCompany/{id}")
    Call<ResponseWrapper<ArrayList<ReviewsEnt>>> getReviewList(@Path("id") int id);

    @FormUrlEncoded
    @POST("review/CreateReview")
    Call<ResponseWrapper> createReview(@Field("UserId") String UserId,
                                       @Field("CompanyId") int CompanyId,
                                       @Field("Points") int Points,
                                       @Field("Analysis") String Analysis,
                                       @Field("IsAnonymous") boolean IsAnonymous);


    @GET("company/GetAllMyFavoritesCount/{id}")
    Call<ResponseWrapper<String>> getFavouriteCount(@Path("id") String id);

    @GET("review/GetAllByUser/{id}")
    Call<ResponseWrapper<ArrayList<ReviewsEnt>>> getAllReviewsOnProfile(@Path("id") String id);

    @GET("company/getallmostpopular/{id}")
    Call<ResponseWrapper<ArrayList<PopularEnt>>> getMostPopularList(@Path("id") String id);

    @GET("category/getallinshowcase")
    Call<ResponseWrapper<ArrayList<ServiceEnt>>> getSerivesList();

    @GET("category/getall")
    Call<ResponseWrapper<ArrayList<ServiceEnt>>> getAllServices();

    @GET("company/getallbycategory/{id}")
    Call<ResponseWrapper<ArrayList<PopularEnt>>> getCategoryDetail(@Path("id") String id);

    @FormUrlEncoded
    @POST("account/updateprofile")
    Call<ResponseWrapper> editProfile(@Field("UserId") String UserId,
                                      @Field("Name") String Name,
                                      @Field("Phone") String Phone,
                                      @Field("City") String City);


}