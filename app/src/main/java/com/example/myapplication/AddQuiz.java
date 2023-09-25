package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddQuiz extends AppCompatActivity {
    private EditText qestionEdt, option1Edt,  option2Edt,  option3Edt,validOptionEdit,id_courseEdit;
    private DBHandler dbHandler;
    private Button addquizButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quiz);

        qestionEdt = findViewById(R.id.editTextQestion);
        option1Edt = findViewById(R.id.editTextoption1);
        option2Edt = findViewById(R.id.editTextoption2);
        option3Edt = findViewById(R.id.editTextoption3);
        validOptionEdit = findViewById(R.id.editTextValidOption);
        id_courseEdit = findViewById(R.id.editTextIdCourse);
        addquizButton=findViewById(R.id.buttonAddQuizz);

        dbHandler = new DBHandler(AddQuiz.this);
        addquizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createquiz();
                sendToAdmin();
            }
        });
    }

    public void createquiz(){
        String qestion=qestionEdt.getText().toString();
        String option1=option1Edt.getText().toString();
        String option2=option2Edt.getText().toString();
        String option3=option3Edt.getText().toString();
        int validOption=Integer.parseInt(validOptionEdit.getText().toString());
        int id_course=Integer.parseInt(id_courseEdit.getText().toString());

        dbHandler.saveQuiz(qestion,option1,option2,option3,validOption,id_course);
        Toast.makeText(AddQuiz.this, "Quizz has been added.", Toast.LENGTH_SHORT).show();
    }
    public void sendToAdmin(){
        Intent intent=new Intent(this,AdminPanel.class);
        startActivity(intent);
    }
}