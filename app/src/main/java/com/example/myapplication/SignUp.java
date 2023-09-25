package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    private EditText user_nameEdt, passwordEdt, phone_numberEdt, emailEdt;
    private Button adduserButton;
    private DBHandler dbHandler;
    private Button button;
    private TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        button=findViewById(R.id.buttonSignUp);
        txt=findViewById(R.id.txtSignIn);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToLogin();
            }
        });
        // pour recupere les valeur du edit text
        user_nameEdt =findViewById(R.id.editTextUsername);
        passwordEdt =findViewById(R.id.editTextPassword);
        phone_numberEdt =findViewById(R.id.editTextPhoneNumber);
        emailEdt =findViewById(R.id.editTextPhoneNumber);

        dbHandler = new DBHandler(SignUp.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createuser();
                sendToLogin();
            }
        });
    }

    public void createuser(){
        String user_name=user_nameEdt.getText().toString();
        String password=passwordEdt.getText().toString();
        String phone_number=phone_numberEdt.getText().toString();
        String email=emailEdt.getText().toString();
        dbHandler.addNewUser(user_name,password,phone_number,email);
        Toast.makeText(SignUp.this, "User has been added.", Toast.LENGTH_SHORT).show();

    }

    public void sendToLogin(){
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
    }
}