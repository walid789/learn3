package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPanel extends AppCompatActivity {
    CardView addNewCourseButton, ListAllCourse, AddLesson, Addquizz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_admin_panel);
        getSupportActionBar().setTitle("Admin Panel");
        ListAllCourse=findViewById(R.id.ListAllCourse);
        ListAllCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToList();
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
}