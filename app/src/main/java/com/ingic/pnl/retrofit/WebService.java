package com.ingic.pnl.retrofit;


import com.ingic.pnl.entities.ResponseWrapper;
import com.ingic.pnl.entities.UserIDEnt;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
    Call<ResponseWrapper> forgotPassword(@Field("Email") String Email);

   /* @GET("/api/company/GetAllMyFavorites")
    Call<ResponseWrapper> traineeProfile(
            @Path("userId") int userId);*/


}