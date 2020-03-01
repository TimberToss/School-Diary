package com.example.schooldiary.mainpage.model;

public class Marks {

    private String subjectName;
    private String marks;

    public Marks() {}

    public Marks(String subjectName, String marks) {
        this.subjectName = subjectName;
        this.marks = marks;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }
}
