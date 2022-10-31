package com.example.fuel;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fuel.Controller.FuelInterface;
import com.example.fuel.Controller.StationInterface;
import com.example.fuel.Controller.UserInterface;
import com.example.fuel.modelClass.StationModel;
import com.example.fuel.modelClass.UserModel;
import com.example.fuel.stationOwner.FuelStationHomepage;
import com.example.fuel.user.FuelStation;
import com.example.fuel.user.Homepage;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//Implementation of Fragment for Sign In UI

public class SignInFragment extends Fragment {


    private UserInterface userInterface ;

    List<UserModel> userModelList;

    String isUserRole="";

    String userForStation="";

//  Implementation of Login
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        userInterface = retrofit.create(UserInterface.class);



        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.sign_in, null);

        //        Email Validation
        TextInputLayout emailField = v.findViewById(R.id.enterEmailField);
        TextInputEditText editEmail = v.findViewById(R.id.editEmailField);

        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(Patterns.EMAIL_ADDRESS.matcher(editEmail.getText().toString()).matches()){
                    emailField.setHelperText("");
                    emailField.setError("");
                }else {
                    emailField.setError("Invalid Email Address");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        TextInputLayout emailLogin = v.findViewById(R.id.enterEmailField);
        TextInputLayout passwordLogin = v.findViewById(R.id.enterPasswordField);
        Button signIn = (Button) v.findViewById(R.id.signInBtn);
        DBHelper DB = new DBHelper(getActivity());

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                Call<UserModel> userModelcall = userInterface.findUser("TEST");
//                System.out.println(" 111111111111111111111111111");
//                userModelcall.enqueue(new Callback<UserModel>() {
//
//                    @Override
//                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
//                        System.out.println(" User findddddddd Successfully");
//
////                        System.out.println("  inside userid 77777 88888888888888  [0]------------- "+  response.body().toString());
//                    }
//                    @Override
//                    public void onFailure(Call<UserModel> call, Throwable t) {
//                        System.out.println(" User findddddddd Failed");
//                    }
//                });

                String email = emailLogin.getEditText().getText().toString();
                String pass = passwordLogin.getEditText().getText().toString();

                if(email.equals("")||pass.equals(""))
                    Toast.makeText(getActivity(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{

                    Boolean checkuserpass = DB.checkExistingUser(email, pass);
                    if(checkuserpass==true){
                        Call<List<UserModel>> call = userInterface.getUser();
                        call.enqueue(new Callback<List<UserModel>>() {
                            @Override
                            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                                System.out.println("user login sucess");
                                if(!response.isSuccessful()){
                                }
                                userModelList =response.body();
                                for(int i=0;i<userModelList.size();i++) {
                                    if(userModelList.get(i).getEmail().equals(email)){
                                        isUserRole=userModelList.get(i).getUserRole();
                                        if(isUserRole.equals("stationAdmin")){
                                            userForStation=userModelList.get(i).getId();
                                        }
                                    }
                                }
                                if(isUserRole.equals("user")){



                                    System.out.println("user----------------------- "+isUserRole);
//
                                    Intent intent = new Intent(getActivity(), Homepage.class);
                                    intent.putExtra("userEmail", email);
                                    startActivity(intent);

                                    ////

                                }else{
                                    System.out.println("admin----------------------- "+userForStation);
                                    Intent intent = new Intent(getActivity(), FuelStationHomepage.class);
                                    intent.putExtra("stationName", userForStation);
                                    intent.putExtra("password", pass);
                                    startActivity(intent);

//                                    System.out.println("admin----------------------- "+isUserRole);
//                                    Intent stationOwnerSide  = new Intent(getActivity(), FuelStationHomepage.class);
//                                    startActivity(stationOwnerSide);
                                }
                            }
                            @Override
                            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                                System.out.println("user login failed");
                            }
                        });

                    }else{
                        Toast.makeText(getActivity(), "User does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return v;

    }
}
