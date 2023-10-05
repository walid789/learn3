package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddCourse extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText editTextCourseName;
    private ImageView imageViewCourseImage;
    private Bitmap courseImageBitmap;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        getSupportActionBar().setTitle("Add New Course");

        // on recupere les image et le zoine de text et les 2 button par ces id
        editTextCourseName = findViewById(R.id.editTextCourseName);
        imageViewCourseImage = findViewById(R.id.imageViewCourseImage);
        Button buttonSelectImage = findViewById(R.id.buttonSelectImage);
        Button buttonSaveCourse = findViewById(R.id.buttonSaveCourse);
        dbHandler = new DBHandler(AddCourse.this);

        // on ajouter un event a le button select-image
        buttonSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open an image picker or camera intent to select an image
                // Here, we'll use an intent to open the device's image gallery
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
            }
        });

        // si click sur le button save
        buttonSaveCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the course name entered by the user
                String courseName = editTextCourseName.getText().toString();
                // Save the course name and image to the database
                if (!courseName.isEmpty() && courseImageBitmap != null) {
                    byte[] imageByteArray = getByteArrayFromBitmap(courseImageBitmap);
                    dbHandler.saveCourse(courseName,imageByteArray);
                    Toast.makeText(AddCourse.this, "course add with success", Toast.LENGTH_SHORT).show();
                    BackToAdminPanel();
                }
            }
        });
    }

    public void BackToAdminPanel(){
          Intent intent=new Intent(this,AdminPanel.class);
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