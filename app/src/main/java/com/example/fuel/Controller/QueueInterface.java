package com.example.fuel.Controller;

import com.example.fuel.modelClass.QueueModel;
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




public interface QueueInterface {

    @GET("Queue")
    Call<List<QueueModel>> getQueue();

    @POST("Queue")
    Call<QueueModel> createQueue(@Body QueueModel queueModel);

    @PUT("Queue/{id}")
    Call<QueueModel> putQueue(@Path("id") String id , @Body QueueModel  queueModel);


    @PATCH("Queue/{id}")
    Call<QueueModel> patchQueue(@Path("id") String id , @Body QueueModel queueModel);

    @DELETE("Queue/{id}")
    Call<QueueModel> deleteQueue(@Path("id") String  id );

}
