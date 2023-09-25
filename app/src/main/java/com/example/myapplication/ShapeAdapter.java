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

import java.util.ArrayList;

public class ShapeAdapter extends ArrayAdapter<Course>
{

    public ShapeAdapter(Context context, int resource, ArrayList<Course> shapeList)
    {
        super(context,resource,shapeList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Course course = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shape_cell, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name);

        ImageView courseImageView = convertView.findViewById(R.id.courseImageView);
        name.setText(course.getName());



        byte[] imageData = course.getImage();
        if (imageData != null && imageData.length > 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            courseImageView.setImageBitmap(bitmap);
        } else {
            // If no image data is available, you can set a default image
            courseImageView.setImageResource(R.drawable.html);
        }
        return convertView;
    }
}
