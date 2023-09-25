package com.example.myapplication;

import static com.example.myapplication.LessonViewAll.LessonList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class quiz extends AppCompatActivity {
    private DBHandler dbHandler;
    public  ArrayList<QuizClass> quizList = new ArrayList<QuizClass>();
    TextView qestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ListView listView = findViewById(R.id.listViewquiz);





        int item_id = getIntent().getIntExtra("id_lesson",0);
        dbHandler = new DBHandler(quiz.this);


        //Log.d("TAG", "our list: "+LessonList.get(item_id).getId());
        int id_lesson =LessonList.get(item_id).getId();
        quizList.addAll(dbHandler.ReadQuizByIdLesson(id_lesson));
        qestion=findViewById(R.id.qestion);
        // Create an array of strings (you can replace this with your own data)
        String[] stringArray = {quizList.get(0).getOption1(), quizList.get(0).getOption2(), quizList.get(0).getOption3()};
        qestion.setText(quizList.get(0).getQuestion());
        // Create an ArrayAdapter using android.R.layout.simple_list_item_1
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringArray);
        // Set the adapter for the ListView
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos=position+1;
                if (pos==quizList.get(0).getValid_option()){
                    Toast.makeText(quiz.this, "correct answer.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(quiz.this, "try again.", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}