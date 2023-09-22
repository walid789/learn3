package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class LessonViewAll extends AppCompatActivity {
    private DBHandler dbHandler;
    public static ArrayList<Lesson> LessonList = new ArrayList<Lesson>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_view_all);
        setupData();
        setUpList();
    }
    private void setupData() {
        LessonList.clear();
        dbHandler = new DBHandler(LessonViewAll.this);
        long item_id = getIntent().getLongExtra("course_id", 1);
        int course_id = (int) item_id;
        Log.d("TAG", "setupData: "+course_id);
        LessonList.addAll(dbHandler.readLesson(course_id));
    }
    private void setUpList() {
        ListView listView = (ListView) findViewById(R.id.LessonListView);
        LessonAdapter adapter = new LessonAdapter(getApplicationContext(), 0, LessonList);
        listView.setAdapter(adapter);
    }
}