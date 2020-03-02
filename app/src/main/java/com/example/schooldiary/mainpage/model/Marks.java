package com.example.schooldiary.mainpage.model;

public class Marks {

    private String name;
    private String marks;

    public Marks() {}

    public Marks(String name, String marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}
