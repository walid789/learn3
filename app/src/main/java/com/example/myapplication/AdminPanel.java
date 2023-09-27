package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPanel extends AppCompatActivity {
    CardView addNewCourseButton, ListAllCourse, AddLesson, Addquizz,Logout,updatequiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_panel);
        getSupportActionBar().setTitle("Admin Panel");
        updatequiz=findViewById(R.id.updatequiz);
        updatequiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToUpdateQuizz();
            }
        });
        ListAllCourse=findViewById(R.id.ListAllCourse);
        ListAllCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToList();
            }
        });
        Logout=findViewById(R.id.logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendtoLogout();
            }
        });

        Addquizz=findViewById(R.id.Addquiz);
        Addquizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToAddQuiz();
            }
        });

        AddLesson=findViewById(R.id.AddLesson);
        AddLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToAddLesson();
            }
        });


        addNewCourseButton=findViewById(R.id.addNewCourse);
        addNewCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToNewCourse();
            }
        });
    }
    public void sendToNewCourse(){
        Intent intent=new Intent(this,AddCourse.class);
        startActivity(intent);
    }
    public void sendToList(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void sendToAddLesson(){
        Intent intent=new Intent(this,AddNewLesson.class);
        startActivity(intent);
    }
    public void sendToAddQuiz(){
        Intent intent=new Intent(this,AddQuiz.class);
        startActivity(intent);
    }
    public void  sendtoLogout(){
        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
    }
    private void sendToUpdateQuizz(){
        Intent intent=new Intent(this,UpdateQuiz.class);
        startActivity(intent);
    }
}