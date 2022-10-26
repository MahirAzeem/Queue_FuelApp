package com.example.fuel.userLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.fuel.R;
import com.example.fuel.userLogin.Login;

public class Register extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        textView=(TextView) findViewById(R.id.signInTxt);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInNavigation();
            }
        });
    }

    public void signInNavigation(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
