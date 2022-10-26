package com.example.fuel.userLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fuel.R;

public class Login extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        textView = (TextView) findViewById(R.id.signUpTxt);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpNavigation();
            }
        });
    }

    public void signUpNavigation(){
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}