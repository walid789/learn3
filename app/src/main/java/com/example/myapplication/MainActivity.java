package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Course> shapeList = new ArrayList<Course>();

    private ListView listView;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupData();
        setUpList();
        setUpOnclickListener();
    }

    private void setupData() {
        shapeList.clear();
        dbHandler = new DBHandler(MainActivity.this);
        shapeList.addAll(dbHandler.readCourses());
    }

    private void setUpList() {
        listView = (ListView) findViewById(R.id.shapesListView);
        ShapeAdapter adapter = new ShapeAdapter(getApplicationContext(), 0, shapeList);
        listView.setAdapter(adapter);
    }
    private void setUpOnclickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                long itemId = id;
                if(itemId==0){
                    itemId++;
                }
                Intent intent = new Intent(MainActivity.this, LessonViewAll.class);
                intent.putExtra("course_id", itemId);
                startActivity(intent);
            }
        });
    }
}
