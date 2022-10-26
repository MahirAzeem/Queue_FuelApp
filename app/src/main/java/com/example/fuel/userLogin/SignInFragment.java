package com.example.fuel.userLogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fuel.DBHelper;
import com.example.fuel.R;
import com.example.fuel.user.Homepage;
import com.google.android.material.textfield.TextInputLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignIn.
     */
    // TODO: Rename and change types and number of parameters
    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_sign_in, null);

        TextInputLayout emailLogin = v.findViewById(R.id.enterEmailField);
        TextInputLayout passwordLogin = v.findViewById(R.id.enterPasswordField);
        Button signIn = (Button) v.findViewById(R.id.signInBtn);
        DBHelper DB = new DBHelper(getActivity());

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailLogin.getEditText().getText().toString();
                String pass = passwordLogin.getEditText().getText().toString();

//                openHomepage();

                if(email.equals("")||pass.equals(""))
                    Toast.makeText(getActivity(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkExistingUser(email, pass);
                    if(checkuserpass==true){
                        Toast.makeText(getActivity(), "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getActivity(), Homepage.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getActivity(), "User does not exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return v;

    }

    public void openHomepage() {
        Intent intent = new Intent(getActivity(), Homepage.class);
        startActivity(intent);
    }
}
