package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    TextView buttonSinUp;
    private Button buttonLogin;
    private DBHandler dbHandler;
    private EditText user_nameEdt, passwordEdt;
    private ArrayList<Course> courseModalArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        buttonSinUp=findViewById(R.id.buttonSignup);
        buttonLogin=findViewById(R.id.buttonLogin);
        user_nameEdt =findViewById(R.id.editTextUsername);
        passwordEdt =findViewById(R.id.editTextPassword);
        dbHandler = new DBHandler(Login.this);
        courseModalArrayList = dbHandler.readCourses();


        buttonSinUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sendToSignUp();
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Authetification();
            }
        });
    }


    public void Authetification(){
        String user_name=user_nameEdt.getText().toString();
        String password=passwordEdt.getText().toString();
        int test= dbHandler.Auth(user_name,password);
        if(test==1){
            Toast.makeText(Login.this, "Auth success for user", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        if(test==2){
            Toast.makeText(Login.this, "Auth success for admin", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,AdminPanel.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(Login.this, "password or username invalid", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendToSignUp(){
        Intent intent=new Intent(this,SignUp.class);
        startActivity(intent);
    }
    }