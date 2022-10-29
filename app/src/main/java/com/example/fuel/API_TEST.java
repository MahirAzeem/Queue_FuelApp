package com.example.fuel;






        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.widget.TextView;

        import com.example.fuel.Controller.StationInterface;
        import com.example.fuel.modelClass.StationModel;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

public class API_TEST extends AppCompatActivity {

    private TextView textViewResults;
    private StationInterface stationInterface ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_test);


        textViewResults = findViewById(R.id.text_view_results);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        stationInterface = retrofit.create(StationInterface.class);

//        createStations();
        getPost();
//        deletePost();
//        updatePost();






    }



    private void getPost() {
        Call<List<StationModel>> call = stationInterface.getStation();
        call.enqueue(new Callback<List<StationModel>>() {
            @Override
            public void onResponse(Call<List<StationModel>> call, Response<List<StationModel>> response) {
                if(!response.isSuccessful()){
                    textViewResults.setText("Code : " +response.code());
                    return;
                }

                List <StationModel> posts = response.body();

                for (StationModel post :posts){
                    String content ="";
                    content += "ID :"+post.getId()+"\n";
                    content += "stationName  :"+post.getStationName()+"\n";
                    content += "location :"+post.getLocation()+"\n";
                    content += "brand :"+post.getBrand()+"\n";
                    content += "............. :\n";


                    textViewResults.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<StationModel>> call, Throwable t) {
                textViewResults.setText(t.getMessage());
            }
        });
    }

    private void createStations() {


        StationModel stationModel = new StationModel("TEST","TEST","TEST");
        Call<StationModel> call = stationInterface.createStation(stationModel);

        call.enqueue(new Callback<StationModel>() {
            @Override
            public void onResponse(Call<StationModel> call, Response<StationModel> response) {
                if(!response.isSuccessful()){
                    textViewResults.setText("Code : " +response.code());
                    return;
                }

                StationModel posts = response.body();


                String content ="";
                content += "ID :"+posts.getId()+"\n";
                content += "stationName  :"+posts.getStationName()+"\n";
                content += "location :"+posts.getLocation()+"\n";
                content += "brand :"+posts.getBrand()+"\n";
                content += "............. :\n";


                textViewResults.append(content);

            }

            @Override
            public void onFailure(Call<StationModel> call, Throwable t) {

            }


        });

    }


    private void updatePost() {


        StationModel post = new StationModel("TEST","TEST","TEST");
        Call<StationModel> call = stationInterface.putStation("jjdddddd",post);

        call.enqueue(new Callback<StationModel>() {
            @Override
            public void onResponse(Call<StationModel> call, Response<StationModel> response) {
                if(!response.isSuccessful()){
                    textViewResults.setText("Code : " +response.code());
                    return;
                }

                StationModel posts = response.body();


                String content ="";
                content += "ID :"+posts.getId()+"\n";
                content += "stationName  :"+posts.getStationName()+"\n";
                content += "location :"+posts.getLocation()+"\n";
                content += "brand :"+posts.getBrand()+"\n";
                content += "............. :\n";


                textViewResults.append(content);

            }

            @Override
            public void onFailure(Call<StationModel> call, Throwable t) {

            }


        });

    }



    private void deletePost() {

        Call<StationModel> call = stationInterface.deleteStation("635892ff6a25ebc564d97fbe");


        call.enqueue(new Callback<StationModel>() {
            @Override
            public void onResponse(Call<StationModel> call, Response<StationModel> response) {
                if(!response.isSuccessful()){
                    textViewResults.setText("Code : " +response.code());
                    return;
                }




                textViewResults.append("deleted.....");

            }

            @Override
            public void onFailure(Call<StationModel> call, Throwable t) {

            }


        });

    }



}