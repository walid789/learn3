package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class UpdateDeleteCourse extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText editTextCourseName;
    private ImageView imageViewCourseImage;
    private Bitmap courseImageBitmap;
    private DBHandler dbHandler;
    public static ArrayList<Course> courseList = new ArrayList<Course>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_course);
        getSupportActionBar().setTitle("Update Or Delete Course");

        courseList.clear();
        dbHandler = new DBHandler(UpdateDeleteCourse.this);
        editTextCourseName = findViewById(R.id.editTextCourseName);
        int course_id = getIntent().getIntExtra("course_id", 0);
        courseList.addAll(dbHandler.readCoursesById(course_id));

        editTextCourseName.setText(courseList.get(0).getName());
        imageViewCourseImage = findViewById(R.id.imageViewCourseImage);
        byte[] imageData = courseList.get(0).getImage();
        if (imageData != null && imageData.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            imageViewCourseImage.setImageBitmap(bitmap);
        } else {
            // If no image data is available, you can set a default image
            imageViewCourseImage.setImageResource(R.drawable.html);
        }
        Button buttonSelectImage = findViewById(R.id.buttonSelectImage);
        Button buttonSaveCourse = findViewById(R.id.buttonSaveCourse);




        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open an image picker or camera intent to select an image
                // Here, we'll use an intent to open the device's image gallery
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });
        buttonSaveCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the course name entered by the user
                String courseName = editTextCourseName.getText().toString();
                int id =courseList.get(0).getId();
                // Save the course name and image to the database
                if (!courseName.isEmpty() && courseImageBitmap != null) {
                    byte[] imageByteArray = getByteArrayFromBitmap(courseImageBitmap);
                    dbHandler.updateCourse(id,courseName,imageByteArray);
                    Toast.makeText(UpdateDeleteCourse.this, "course update with success", Toast.LENGTH_SHORT).show();
                    BackToList();
                }
            }
        });
    }

    public void BackToList(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null) {
            // Handle the selected image
            try {
                // Get the selected image URI
                Uri selectedImageUri = data.getData();

                // Load the selected image into the ImageView
                courseImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                imageViewCourseImage.setImageBitmap(courseImageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper method to convert a Bitmap to a byte array
    private byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}