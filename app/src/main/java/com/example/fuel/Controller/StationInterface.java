package com.example.fuel.Controller;


import com.example.fuel.modelClass.StationModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;




public interface StationInterface {

    @GET("Station")
    Call<List<StationModel>> getStation();

    @POST("Station")
    Call<StationModel> createStation(@Body StationModel stationModel);

    @PUT("Station/{id}")
    Call<StationModel> updateStation(@Path("id") String id , @Body StationModel stationModel);


    @PATCH("Station/{id}")
    Call<StationModel> patchStation(@Path("id") String id , @Body StationModel stationModel);

    @DELETE("Station/{id}")
    Call<StationModel> deleteStation(@Path("id") String  id );

}
