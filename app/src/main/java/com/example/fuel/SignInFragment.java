package com.example.fuel;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fuel.stationOwner.FuelStationHomepage;
import com.example.fuel.user.Homepage;
import com.google.android.material.textfield.TextInputLayout;


//Implementation of Fragment for Sign In UI

public class SignInFragment extends Fragment {


//  Implementation of Login
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.sign_in, null);

        TextInputLayout emailLogin = v.findViewById(R.id.enterEmailField);
        TextInputLayout passwordLogin = v.findViewById(R.id.enterPasswordField);
        Button signIn = (Button) v.findViewById(R.id.signInBtn);
        DBHelper DB = new DBHelper(getActivity());

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailLogin.getEditText().getText().toString();
                String pass = passwordLogin.getEditText().getText().toString();

                if(email.equals("")||pass.equals(""))
                    Toast.makeText(getActivity(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkExistingUser(email, pass);
                    if(checkuserpass==true){
                        Toast.makeText(getActivity(), "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent userSide  = new Intent(getActivity(), Homepage.class);
                        startActivity(userSide);

//                        Intent stationOwnerSide  = new Intent(getActivity(), FuelStationHomepage.class);
//                        startActivity(stationOwnerSide);
                    }else{
                        Toast.makeText(getActivity(), "User does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return v;

    }
}
