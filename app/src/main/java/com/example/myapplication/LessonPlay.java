package com.example.myapplication;

import static com.example.myapplication.LessonViewAll.LessonList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class LessonPlay extends AppCompatActivity {
    private DBHandler dbHandler;
    int item_id;
    Button button,goback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_play);
        getSupportActionBar().setTitle("Lesson Play");
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView paragraphTextView = findViewById(R.id.paragraphTextView);
        TextView codeWebView = findViewById(R.id.codeWebView);
        WebView webView=findViewById(R.id.videoyout);



        dbHandler = new DBHandler(LessonPlay.this);
        //LessonList.addAll(dbHandler.readLesson(course_id));

        item_id = getIntent().getIntExtra("postion", 1);
        // Set the title and paragraph text
        titleTextView.setText(LessonList.get(item_id).getTitle());
        paragraphTextView.setText(LessonList.get(item_id).getParagraphe());
        codeWebView.setText(LessonList.get(item_id).getCode_playground());
        String video=LessonList.get(item_id).getYoutube_url();
        webView.loadData(video,  "text/html",  "utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        if (18 < Build.VERSION.SDK_INT ){
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        }
        button=findViewById(R.id.quiz);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToquiz();
            }
        });
        goback=findViewById(R.id.goback);
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendtoAdmin();
            }
        });

    }
private void sendtoAdmin(){
    Intent intent=new Intent(this,AdminPanel.class);
    startActivity(intent);
}
    public void sendToquiz(){
        int id_lesson=item_id;
        Intent intent=new Intent(this,quiz.class);
        intent.putExtra("id_lesson", id_lesson);
        startActivity(intent);
    }

}