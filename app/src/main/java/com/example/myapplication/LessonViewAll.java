package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class LessonViewAll extends AppCompatActivity {
    private DBHandler dbHandler;
    private ListView listView;
    public int course_id ;
    public static ArrayList<Lesson> LessonList = new ArrayList<Lesson>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_view_all);
        getSupportActionBar().setTitle("Desicover Lesson");
        setupData();
        setUpList();
        setUpOnclickListener();
        setupUpLongclick();
    }
    private void setupData() {
        LessonList.clear();
        dbHandler = new DBHandler(LessonViewAll.this);
        int course_id = getIntent().getIntExtra("course_id", 0);
        LessonList.addAll(dbHandler.readLesson(course_id));
    }
    private void setUpList() {
         listView = (ListView) findViewById(R.id.LessonListView);
        LessonAdapter adapter = new LessonAdapter(getApplicationContext(), 0, LessonList);
        listView.setAdapter(adapter);
    }
    private void setUpOnclickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "onItemClick: "+LessonList.get(position).getName());
                Intent intent = new Intent(LessonViewAll.this, LessonPlay.class);
                intent.putExtra("postion",position);
                intent.putExtra("course_id",course_id);

                startActivity(intent);
            }
        });
    }
    private void setupUpLongclick(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LessonViewAll.this, UpdateLesson.class);
                intent.putExtra("id_lesson",position);
                startActivity(intent);
                return true;
            }
        });
    }





}