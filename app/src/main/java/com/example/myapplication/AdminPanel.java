package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminPanel extends AppCompatActivity {
    Button addNewCourseButton;
    Button ListAllCourse;
    Button AddLesson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        ListAllCourse=findViewById(R.id.ListAllCourse);
        ListAllCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToList();
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
}