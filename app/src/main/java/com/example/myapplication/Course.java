package com.example.myapplication;

public class Course {
    private  String name;
    private byte[] image;
    private  int id;

    public Course(int id , String name, byte[] image) {
        this.id=id;
        this.name = name;
        this.image = image;
    }

    // Getter methods for name and image
    public  String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }

    // Setter methods (if needed)
    public void setName(String name) {
        this.name = name;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    public  int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

