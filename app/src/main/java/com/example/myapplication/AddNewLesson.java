package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewLesson extends AppCompatActivity {
    private EditText editTextId;
    private EditText editTextName;
    private EditText editTextTitle;
    private EditText editTextParagraph;
    private EditText editTextCodePlayground,editTextyoutubeUrl;
    private Button addButton;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lesson);
        getSupportActionBar().hide();
        editTextyoutubeUrl=findViewById(R.id.youtube_url);
        // Associate views with variables
        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextParagraph = findViewById(R.id.editTextParagraph);
        editTextCodePlayground = findViewById(R.id.editTextCodePlayground);
        addButton = findViewById(R.id.addButton);
        dbHandler = new DBHandler(AddNewLesson.this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TextId=editTextId.getText().toString();
                int id = Integer.parseInt(TextId);
                String Name=editTextName.getText().toString();
                String Title=editTextTitle.getText().toString();
                String Paragraph=editTextParagraph.getText().toString();
                String CodePlayground =editTextCodePlayground.getText().toString();
                String ulr_youtube=editTextyoutubeUrl.getText().toString();
                dbHandler.addNewLesson(id,Name,Title,Paragraph,CodePlayground,ulr_youtube);
                Toast.makeText(AddNewLesson.this, "Data added!", Toast.LENGTH_SHORT).show();
                sendtoAdminPanel();
            }
        });
    }
    private  void sendtoAdminPanel(){
        Intent intent=new Intent(this,AdminPanel.class);
        startActivity(intent);}
}