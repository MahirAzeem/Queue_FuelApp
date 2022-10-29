package com.example.fuel.Controller;

import com.example.fuel.modelClass.StationModel;
import com.example.fuel.modelClass.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;




public interface UserInterface {

    @GET("User")
    Call<List<UserModel>> getUser();

    @POST("User")
    Call<UserModel> createUser(@Body UserModel userModel);

    @PUT("User/{id}")
    Call<UserModel> putUser(@Path("id") String id , @Body UserModel  userModel);


    @PATCH("User/{id}")
    Call<UserModel> patchUser(@Path("id") String id , @Body UserModel userModel);

    @DELETE("User/{id}")
    Call<UserModel> deleteUser(@Path("id") String  id );

}
