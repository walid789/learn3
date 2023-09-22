package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class List_all extends AppCompatActivity {
    private ListView courseListView;
     private List_adpater_course list_adpater_course;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        dbHandler = new DBHandler(List_all.this);

        courseListView = findViewById(R.id.ListView);
        ArrayList<Course> courseList =  dbHandler.readCourses();

        list_adpater_course = new List_adpater_course(getApplicationContext(), 0,  dbHandler.readCourses());
        courseListView.setAdapter(list_adpater_course);
    }
}