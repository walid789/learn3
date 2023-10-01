package com.example.myapplication;

public class Lesson {
    private int id;
    private String name;
    private String title;
    private String paragraphe;
    private String code_playground;
    private int  id_course;
    private String youtube_url;

    public Lesson(int id, String name, String title, String paragraphe, String code_playground, int id_course , String youtube_url) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.paragraphe = paragraphe;
        this.code_playground = code_playground;
        this.id_course = id_course;
        this.youtube_url=youtube_url;
    }
    public String getYoutube_url() {
        return youtube_url;
    }
    public void setYoutube_url(String youtube_url) {
        this.youtube_url = youtube_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParagraphe() {
        return paragraphe;
    }

    public void setParagraphe(String paragraphe) {
        this.paragraphe = paragraphe;
    }

    public String getCode_playground() {
        return code_playground;
    }

    public void setCode_playground(String code_playground) {
        this.code_playground = code_playground;
    }

    public int getId_course() {
        return id_course;
    }

    public void setId_course(int id_course) {
        this.id_course = id_course;
    }
}
