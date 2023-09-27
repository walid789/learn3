package com.example.myapplication;

import static com.example.myapplication.LessonViewAll.LessonList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LessonPlay extends AppCompatActivity {
    private DBHandler dbHandler;
    int item_id;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_play);
        getSupportActionBar().setTitle("Lesson Play");
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView paragraphTextView = findViewById(R.id.paragraphTextView);
        TextView codeWebView = findViewById(R.id.codeWebView);
        dbHandler = new DBHandler(LessonPlay.this);
        //LessonList.addAll(dbHandler.readLesson(course_id));

        item_id = getIntent().getIntExtra("postion", 1);
        // Set the title and paragraph text
        titleTextView.setText(LessonList.get(item_id).getTitle());
        paragraphTextView.setText(LessonList.get(item_id).getParagraphe());
        codeWebView.setText(LessonList.get(item_id).getCode_playground());

        button=findViewById(R.id.quiz);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToquiz();
            }
        });
    }

    public void sendToquiz(){
        int id_lesson=item_id;
        Intent intent=new Intent(this,quiz.class);
        intent.putExtra("id_lesson", id_lesson);
        startActivity(intent);
    }
}