package com.example.fuel.Controller;

import com.example.fuel.modelClass.FuelModel;
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




public interface FuelInterface {

    @GET("Fuel")
    Call<List<FuelModel>> getFuel();

    @POST("Fuel")
    Call<FuelModel> createFuel(@Body FuelModel fuelModel);

    @PUT("Fuel/{id}")
    Call<FuelModel> putFuel(@Path("id") String id , @Body FuelModel fuelModel);


    @PATCH("Fuel/{id}")
    Call<FuelModel> patchFuel(@Path("id") String id , @Body FuelModel fuelModel);

    @DELETE("Fuel/{id}")
    Call<UserModel> deleteFuel(@Path("id") String  id );

}
