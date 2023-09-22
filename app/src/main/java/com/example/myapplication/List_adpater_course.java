package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class List_adpater_course extends ArrayAdapter<Course>
{
    private List<Course> shapeList;

    public List_adpater_course(Context context, int resource, List<Course> shapeList)
    {
        super(context,resource,shapeList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

     //   TextView courseNameTextView = convertView.findViewById(R.id.courseNameTextView);
      // ImageView courseImageView = convertView.findViewById(R.id.courseImageView);
        Course course = shapeList.get(position);

        //courseNameTextView.setText(course.getName());

        // Set the course image here
        /*byte[] imageData = course.getImage();
        if (imageData != null && imageData.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            courseImageView.setImageBitmap(bitmap);
        } else {
            // If no image data is available, you can set a default image
            courseImageView.setImageResource(R.drawable.html);
        }*/

        return convertView;
    }
}
