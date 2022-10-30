package com.example.fuel.userLogin;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fuel.Controller.UserInterface;
import com.example.fuel.DBHelper;
import com.example.fuel.Login_SignUp_Interface;
import com.example.fuel.R;
import com.example.fuel.modelClass.UserModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//Implementation of User Sign Up
public class User_SignUpFragment extends Fragment {


    private UserInterface userInterface ;




//    Providing value for User District Dropdown

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ahmedameer-001-site1.atempurl.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userInterface = retrofit.create(UserInterface.class);

        String[] UserDistrict = {"Colombo", "Galle", "Gampaha", "Jaffna", "Kandy"};
        AutoCompleteTextView userDistrict;
        ArrayAdapter<String> adapteruserDistrict;

        View v =inflater.inflate(R.layout.user_sign_up, null);

//        Email Validation
        TextInputLayout emailField = v.findViewById(R.id.enterUserEmailField);
        TextInputEditText editEmail = v.findViewById(R.id.userEmailEdit);

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



//        Password Validation
        TextInputLayout passwordField = v.findViewById(R.id.enterUserPasswordField);
        TextInputEditText editPassword = v.findViewById(R.id.userPasswordEdit);

        editPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String password = charSequence.toString();
                if(password.length() >= 5) {
                    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher = pattern.matcher(password);
                    boolean isPwdContainsSpeChar = matcher.find();

                    if(isPwdContainsSpeChar){
                        passwordField.setHelperText("Strong Password");
                        passwordField.setError("");
                    }else{
                        passwordField.setHelperText("");
                        passwordField.setError("Weak Password, Include minimum 1 special character");
                    }
                }else {
                    passwordField.setHelperText("Enter minimum 5 characters");
                    passwordField.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



                userDistrict = v.findViewById(R.id.UserDistrict);
        adapteruserDistrict = new ArrayAdapter<String>(getActivity(), R.layout.list_item, UserDistrict);
        userDistrict.setAdapter(adapteruserDistrict);
        userDistrict.setThreshold(5);


        final String[] userDistrictValue = new String[1];

//      Extracting the dropdown value using setOnItemClickListener
        userDistrict.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                userDistrictValue[0] = adapterView.getItemAtPosition(position).toString();
            }
        });


        TextInputLayout userEmail = v.findViewById(R.id.enterUserEmailField);
        TextInputLayout userPassword = v.findViewById(R.id.enterUserPasswordField);
        TextInputLayout userPhoneNumber = v.findViewById(R.id.enterUserPhoneNoField);
        TextInputLayout userDrivingLicense = v.findViewById(R.id.enterUserDrivingLicenseNo);
        Button UserSignUpButton = v.findViewById(R.id.userSignUpBtn);
        DBHelper DB = new DBHelper(getActivity());

//        Register User when Sign Up button is clicked
        UserSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String user_Email = userEmail.getEditText().getText().toString();
                String user_Password = userPassword.getEditText().getText().toString();
                String user_District = String.valueOf(userDistrictValue);
                String user_PhoneNumber = userPhoneNumber.getEditText().getText().toString();
                String user_DrivingLicense = userDrivingLicense.getEditText().getText().toString();



                UserModel userModel = new UserModel(user_Email, user_Password,user_PhoneNumber,"user",user_DrivingLicense);
                Call<UserModel> userModelcall = userInterface.createUser(userModel);
                userModelcall.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        System.out.println(" User Created Successfully");
                        Boolean checkExistingUser = DB.checkExistingUser(user_Email, user_Password);

                        if (user_Email.equals("") || user_Password.equals("") || user_PhoneNumber.equals("") || user_District.equals("") || user_DrivingLicense.equals("")) {
                            Toast.makeText(getActivity(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                        } else if (checkExistingUser == false) {
                            Boolean registerUser = DB.insertUser(user_Email, user_Password);
                            if (registerUser == true) {
                                Toast.makeText(getActivity(), "You have successfully registered", Toast.LENGTH_SHORT).show();
                                Intent moveToLogin = new Intent(getActivity(), Login_SignUp_Interface.class);
                                startActivity(moveToLogin);
                            } else {
                                Toast.makeText(getActivity(), "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getActivity(), "User already exists!", Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        System.out.println(" User Created Failed");
                    }
                });



            }});

        return v;

    }
}