package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
        getSupportActionBar().setTitle("Descover Course");
        setupData();
        setUpList();
        setUpOnclickListener();
        setUpOnLongclickListener();
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

    private void setUpOnclickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<Course> adapter = (ArrayAdapter<Course>) parent.getAdapter();

                // Retrieve the Lesson object from the adapter using the position
                Course course = adapter.getItem(position);
                int id_course = course.getId();
                Log.d("TAG", "onItemClick: " + id_course);
                Intent intent = new Intent(MainActivity.this, LessonViewAll.class);
                intent.putExtra("course_id", id_course);
                startActivity(intent);
            }
        });
    }

    public void setUpOnLongclickListener() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<Course> adapter = (ArrayAdapter<Course>) parent.getAdapter();
                Course course = adapter.getItem(position);
                int id_course = course.getId();
                Log.d("TAG", "long : " + id_course);
                Intent intent = new Intent(MainActivity.this, UpdateDeleteCourse.class);
                intent.putExtra("course_id", id_course);
                startActivity(intent);
                return true;
            }
        });
    }
}
