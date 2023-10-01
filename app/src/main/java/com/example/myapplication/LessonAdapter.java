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

public class LessonAdapter extends ArrayAdapter<Lesson>
{

    public LessonAdapter(Context context, int resource, ArrayList<Lesson> LessonList)
    {
        super(context,resource,LessonList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Lesson lesson = getItem(position);
        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView lessonName = (TextView) convertView.findViewById(R.id.LessonName);
        lessonName.setText(lesson.getName());
        return convertView;
    }
}
