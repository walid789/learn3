package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateQuiz extends AppCompatActivity {
    private EditText qestionEdt, option1Edt,  option2Edt,  option3Edt,validOptionEdit,id_LessonEdit,idsearch;
    private DBHandler dbHandler;
    private Button updatesuizButton,updatequiz,deletbutton;
    private ArrayList<QuizClass>ListQuiz =new ArrayList<QuizClass>();
    String valueidsearch;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_quiz);
        getSupportActionBar().hide();

        qestionEdt = findViewById(R.id.editTextQestion);
        option1Edt = findViewById(R.id.editTextoption1);
        option2Edt = findViewById(R.id.editTextoption2);
        option3Edt = findViewById(R.id.editTextoption3);
        validOptionEdit = findViewById(R.id.editTextValidOption);
        id_LessonEdit = findViewById(R.id.editTextIdLesson);
        updatequiz=findViewById(R.id.buttonsearch);
        dbHandler = new DBHandler(UpdateQuiz.this);

        updatequiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // en  va chercher les quiz par id
                // dabord en recupere le valeur en suit en appler le fonction readquiz By id
                idsearch=findViewById(R.id.editextidSearch);
                 valueidsearch=idsearch.getText().toString();
                 id=Integer.parseInt(valueidsearch);
                ListQuiz=dbHandler.readQuizById(id);

                //  si le quiz est trouve alors affcter les valeur dans les edite text
                if(ListQuiz.size()==1){
               qestionEdt.setText(ListQuiz.get(0).getQuestion());
               option1Edt.setText(ListQuiz.get(0).getOption1());
                option2Edt.setText(ListQuiz.get(0).getOption2());
                option3Edt.setText(ListQuiz.get(0).getOption3());
                validOptionEdit.setText(Integer.toString(ListQuiz.get(0).getValid_option()));
                id_LessonEdit.setText(Integer.toString(ListQuiz.get(0).getId_lesson()));}
                else{
                    Toast.makeText(UpdateQuiz.this, "quiz not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
        updatesuizButton=findViewById(R.id.buttonAddQuizz);
        //ajouter un event a button puis recupere les valeur saisire par admin
        updatesuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qestion= qestionEdt.getText().toString();
                String option1= option1Edt.getText().toString();
                String option2= option2Edt.getText().toString();
                String option3= option3Edt.getText().toString();
                int validOption= Integer.parseInt(validOptionEdit.getText().toString());
                int id_lesson= Integer.parseInt(id_LessonEdit.getText().toString());
                String valueidsearch1=idsearch.getText().toString();
                int id1=Integer.parseInt(valueidsearch1);
                // on applelr les fonction update quiz qui setuie dans dbhandler
                dbHandler.updateQuiz(id1,qestion,option1,option2,option3,validOption,id_lesson);
                sendtoAdminPanel();
            }
        });

        deletbutton=findViewById(R.id.deletbutton);
        // on ajouter un evenment a notre button delete et on appler le methode deletquiz avec parametre id de quiz
        deletbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.deletequiz(id);
                Toast.makeText(UpdateQuiz.this, "quiz has deleted", Toast.LENGTH_SHORT).show();
                sendtoAdminPanel();
            }
        });
    }
    private  void sendtoAdminPanel(){
    Intent intent=new Intent(this,AdminPanel.class);
    startActivity(intent);
    }
}