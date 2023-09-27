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
 // add new quiz
    public void saveQuiz(String question, String option1, String option2, String option3,int valid_option ,int  id_lesson) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("question", question);
        values.put("option1", option1);
        values.put("option2", option2);
        values.put("option3", option3);
        values.put("valid_option", valid_option);
        values.put("id_lesson", id_lesson);
        database.insert("quiz", null, values);
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

    public void updateCourse(int courseId, String newName, byte[] newImage) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", newName);
        values.put("image", newImage);

        // Define the WHERE clause to specify which course to update based on the ID
        String whereClause = "id = ?";
        String[] whereArgs = { String.valueOf(courseId) };

        // Update the course in the database
        int numRowsUpdated = db.update("course", values, whereClause, whereArgs);

        // Check if the update was successful
        if (numRowsUpdated > 0) {
            Log.d("Database", "Course updated successfully");
        } else {
            Log.d("Database", "Failed to update course");
        }

        db.close();
    }

    public void updateLesson(int id, String name, String title, String paragraph, String code_playground, int id_course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("title", title);
        values.put("paragraphe", paragraph); // Corrected column name
        values.put("code_playground", code_playground);
        values.put("id_course", id_course);

        // Define the WHERE clause to specify which lesson to update based on the ID
        String whereClause = "id = ?";
        String[] whereArgs = { String.valueOf(id) };

        // Update the lesson in the database
        int numRowsUpdated = db.update("lesson", values, whereClause, whereArgs);

        // Check if the update was successful
        if (numRowsUpdated > 0) {
            Log.d("Database", "Lesson updated successfully"); // Corrected log message
        } else {
            Log.d("Database", "Lesson not updated"); // Corrected log message
        }

        db.close();
    }

    public void updateQuiz(int id, String question, String option1, String option2, String option3, int valid_option, int id_lesson) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("question", question);
        values.put("option1", option1);
        values.put("option2", option2);
        values.put("option3", option3);
        values.put("option3", option3);
        values.put("valid_option", valid_option);
        values.put("id_lesson", id_lesson);

        // Define the WHERE clause to specify which lesson to update based on the ID
        String whereClause = "id = ?";
        String[] whereArgs = { String.valueOf(id) };

        // Update the lesson in the database
        int numRowsUpdated = db.update("quiz", values, whereClause, whereArgs);

        // Check if the update was successful
        if (numRowsUpdated > 0) {
            Log.d("Database", "quiz updated successfully"); // Corrected log message
        } else {
            Log.d("Database", "quiz not updated"); // Corrected log message
        }

        db.close();
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

        return courseModalArrayList;
    }
    /*public ArrayList<Lesson> readLessonByid(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursorLesson = db.rawQuery("SELECT id, name, image FROM " + "lesson" + " WHERE id = ?", new String[]{String.valueOf(id)});

        ArrayList<Lesson> courseModalArrayList
                = new ArrayList<Lesson>();
        int rowCount = cursorLesson.getCount();
        Log.d("RowCount", "Number of Rows: " + rowCount);
        // moving our cursor to first position.

        while (cursorLesson.moveToNext()) {
            // on below line we are adding the data from
            // cursor to our array list.
            Course course = new Course(
                    cursorLesson.getInt(cursorLesson.getColumnIndexOrThrow("id")),
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

        return courseModalArrayList;
    }*/

    public ArrayList<QuizClass> readQuizById(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT question,option1,option2,option3,valid_option,id_lesson FROM " + "quiz" + " WHERE id = ?", new String[]{String.valueOf(id)});
        ArrayList<QuizClass> quizModalArrayList
                = new ArrayList<QuizClass>();
        while (cursorCourses.moveToNext()) {
            QuizClass course = new QuizClass(
                    cursorCourses.getString(cursorCourses.getColumnIndexOrThrow("question")),
                    cursorCourses.getString(cursorCourses.getColumnIndexOrThrow("option1")),
                    cursorCourses.getString(cursorCourses.getColumnIndexOrThrow("option2")),
                    cursorCourses.getString(cursorCourses.getColumnIndexOrThrow("option3")),
                    cursorCourses.getInt(cursorCourses.getColumnIndexOrThrow("valid_option")),
                    cursorCourses.getInt(cursorCourses.getColumnIndexOrThrow("id_lesson"))
            );
            quizModalArrayList.add(course);
        }
        cursorCourses.close();
        db.close();
        return quizModalArrayList;
    }
    public ArrayList<Course> readCoursesById(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT id, name, image FROM " + TABLE_course + " WHERE id = ?", new String[]{String.valueOf(id)});
        ArrayList<Course> courseModalArrayList
                = new ArrayList<Course>();
        while (cursorCourses.moveToNext()) {
            Course course = new Course(
                    cursorCourses.getInt(cursorCourses.getColumnIndexOrThrow("id")),
                    cursorCourses.getString(cursorCourses.getColumnIndexOrThrow("name")),
                    cursorCourses.getBlob(cursorCourses.getColumnIndexOrThrow("image"))
            );
            courseModalArrayList.add(course);
        }
        cursorCourses.close();
        db.close();
        return courseModalArrayList;
    }



    /* read lesson by id */
    public ArrayList<QuizClass> ReadQuizByIdLesson (int id)
    {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorQuiz = db.rawQuery("SELECT question,option1,option2,option3,valid_option,id_lesson FROM " + "quiz" + " WHERE id_lesson = ?", new String[]{String.valueOf(id)});
        ArrayList<QuizClass> QUIZModalArrayList
                = new ArrayList<QuizClass>();
        while (cursorQuiz.moveToNext()) {
            QuizClass quiz = new QuizClass(
                    cursorQuiz.getString(cursorQuiz.getColumnIndexOrThrow("question")),
                    cursorQuiz.getString(cursorQuiz.getColumnIndexOrThrow("option1")),
                    cursorQuiz.getString(cursorQuiz.getColumnIndexOrThrow("option2")),
                    cursorQuiz.getString(cursorQuiz.getColumnIndexOrThrow("option3")),
                    cursorQuiz.getInt(cursorQuiz.getColumnIndexOrThrow("valid_option")),
                    cursorQuiz.getInt(cursorQuiz.getColumnIndexOrThrow("id_lesson"))
            );
            QUIZModalArrayList.add(quiz);
        }
        cursorQuiz.close();
        db.close();
        return QUIZModalArrayList;
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

        cursorCourses.close();
        db.close();
        return courseModalArrayList;
    }
        @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
