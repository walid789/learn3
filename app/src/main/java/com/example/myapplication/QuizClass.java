package com.example.myapplication;

public class QuizClass {
   private int  id;
    private String  question;
    private String  option1;
    private String     option2;
    private String option3;
    private int     valid_option;
    private int  id_lesson;

    public QuizClass(String question, String option1, String option2, String option3, int valid_option, int id_lesson) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.valid_option = valid_option;
        this.id_lesson = id_lesson;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public int getValid_option() {
        return valid_option;
    }

    public void setValid_option(int valid_option) {
        this.valid_option = valid_option;
    }

    public int getId_lesson() {
        return id_lesson;
    }

    public void setId_lesson(int id_lesson) {
        this.id_lesson = id_lesson;
    }
}
