package com.example.myapplication;

import static com.example.myapplication.LessonViewAll.LessonList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateLesson extends AppCompatActivity {
    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextTitle;
    private EditText editTextParagraph;
    private EditText editTextCodePlayground;
    private Button addButton;
    private DBHandler dbHandler;
    public int id_lesson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_lesson);
        getSupportActionBar().hide();
        addButton = findViewById(R.id.addButton);
        dbHandler = new DBHandler(UpdateLesson.this);
        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextParagraph = findViewById(R.id.editTextParagraph);
        editTextCodePlayground = findViewById(R.id.editTextCodePlayground);
        setupdata();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id_course=editTextId.getText().toString();
                int n = Integer.parseInt(id_course);

                String name=editTextName.getText().toString();
                String title=editTextTitle.getText().toString();
                String Paragraph=editTextParagraph.getText().toString();
                String CodePlayground=editTextCodePlayground.getText().toString();
                Log.d("TAG", "id lesson: "+id_lesson);
                Log.d("TAG", "id course: "+n);
                dbHandler.updateLesson(id_lesson+1,name,title,Paragraph,CodePlayground,n);
                Toast.makeText(UpdateLesson.this, "Lesson Update Seccuful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateLesson.this, LessonViewAll.class);
                intent.putExtra("course_id", n);
                startActivity(intent);
            }
        });
    }
    public  void setupdata(){
        id_lesson =getIntent().getIntExtra("id_lesson",0);
        String c=String.valueOf(LessonList.get(id_lesson).getId_course());
        Log.d("TAG", "setupdata: "+c);
        editTextId.setText(c);
        editTextName.setText(LessonList.get(id_lesson).getName());
        editTextTitle.setText(LessonList.get(id_lesson).getTitle());
        editTextParagraph.setText(LessonList.get(id_lesson).getParagraphe());
        editTextCodePlayground.setText(LessonList.get(id_lesson).getCode_playground());
    }

}