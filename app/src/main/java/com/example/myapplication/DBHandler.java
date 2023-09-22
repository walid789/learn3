package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {
    //private static final String DB_NAME = "coursedb";
    private static final String DB_NAME1 = "test";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "user";


    private static final String ID_COL = "id";

    private static final String UserName_COL = "user_name";

    private static final String Phone_Number_COL = "phone_number";

    private static final String Password_COL = "password";

    private static final String Email_COL = "Email";


    /* table course */
    private static final String TABLE_course = "course";
        private static final String COLUMN_ID = "id";
        private static final String  COLUMN_name= "course_name";
        private static final String COLUMN_IMAGE = "image";


    public DBHandler(Context context) {
        //super(context, DB_NAME, null, DB_VERSION);
        super(context, DB_NAME1, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserName_COL + " TEXT,"
                + Password_COL + " TEXT,"
                + Phone_Number_COL + " TEXT,"
                + Email_COL + " TEXT)";


        String query3 = "CREATE TABLE " + TABLE_course + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_name + " TEXT,"
                + COLUMN_IMAGE + " BLOB )";
        Log.d("DBHandler", "Create Course Table Query: " + query3);

        db.execSQL("create table course (id integer PRIMARY KEY AUTOINCREMENT,name text,image blob );");
        db.execSQL(query);
       // db.execSQL(query3);
    }

    public void addNewLesson(int id_course  ,String name ,String title ,String pargraphe ,String code_playground) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_course", id_course);
        values.put("name", name);
        values.put("title", title);
        values.put("paragraphe", pargraphe);
        values.put("code_playground", code_playground);
        db.insert("lesson", null, values);
        db.close();
    }


    public void addNewUser(String UserName, String  Password, String Phone_Number, String Email) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserName_COL, UserName);
        values.put(Password_COL, Password);
        values.put(Phone_Number_COL, Phone_Number);
        values.put(Email_COL, Email);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // this function to save a course
    public void saveCourse(String courseName, byte[] imageData) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", courseName);
        values.put("image", imageData);
        database.insert("course", null, values);
        database.close();
    }

    public int Auth(String UserName, String  Password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorUser = db.rawQuery( "SELECT user_name FROM " + TABLE_NAME +
                " WHERE " + UserName_COL + " = ? AND " + Password_COL + " = ?", new String[]{UserName, Password});

        int isAuthenticated = 0;
        String username = "";

        if (cursorUser != null) {
            if (cursorUser.moveToFirst() ) {
                username = cursorUser.getString(cursorUser.getColumnIndexOrThrow("user_name"));
                if(username.equals("admin")){
                    isAuthenticated = 2;
                }
                else
                {
                    isAuthenticated = 1;
                }
            }
            cursorUser.close(); // Close the cursor
        }

        db.close(); // Close the database
        return isAuthenticated;
    }

    // we have created a new method for reading all the courses.
    public ArrayList<Course> readCourses()
    {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to
        // read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT id , name ,image FROM course " + TABLE_course, null);

        // on below line we are creating a new array list.
        ArrayList<Course> courseModalArrayList
                = new ArrayList<Course>();
        int rowCount = cursorCourses.getCount();
        Log.d("RowCount", "Number of Rows: " + rowCount);
          // moving our cursor to first position.

            while (cursorCourses.moveToNext()) {
                // on below line we are adding the data from
                // cursor to our array list.
                Course course = new Course(
                        cursorCourses.getInt(cursorCourses.getColumnIndexOrThrow("id")),
                        cursorCourses.getString(cursorCourses.getColumnIndexOrThrow("name")),
                        cursorCourses.getBlob(cursorCourses.getColumnIndexOrThrow("image"))
                );
                courseModalArrayList.add(course);
            }

            // moving our cursor to next.

        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        db.close();
        /*for (int i = 0; i < courseModalArrayList.size(); i++) {
            Log.d("CourseList", "Item " + i + ": " + courseModalArrayList.get(i).getName());
        }*/
        return courseModalArrayList;
    }



    public ArrayList<Lesson> readLesson(int id_course)
    {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to
        // read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT id,name,title,paragraphe,code_playground,id_course  FROM Lesson where id_course=" + id_course, null);

        // on below line we are creating a new array list.
        ArrayList<Lesson> courseModalArrayList
                = new ArrayList<Lesson>();
        /*int rowCount = cursorCourses.getCount();
        Log.d("RowCount", "Number of Rows: " + rowCount);*/
        // moving our cursor to first position.

        while (cursorCourses.moveToNext()) {
            // on below line we are adding the data from
            // cursor to our array list.
            Lesson lesson = new Lesson(
                    cursorCourses.getInt(cursorCourses.getColumnIndexOrThrow("id")),
                    cursorCourses.getString(cursorCourses.getColumnIndexOrThrow("name")),
                    cursorCourses.getString(cursorCourses.getColumnIndexOrThrow("title")),
                    cursorCourses.getString(cursorCourses.getColumnIndexOrThrow("paragraphe")),
                    cursorCourses.getString(cursorCourses.getColumnIndexOrThrow("code_playground")),
                    cursorCourses.getInt(cursorCourses.getColumnIndexOrThrow("id_course")));
                    courseModalArrayList.add(lesson);
        }

        // moving our cursor to next.

        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        db.close();
        /*for (int i = 0; i < courseModalArrayList.size(); i++) {
            Log.d("CourseList", "Item " + i + ": " + courseModalArrayList.get(i).getName());
        }*/
        return courseModalArrayList;
    }



        @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
