package com.example.schooldiary.model;

public class Subject {

    private String name;
    private String homework;
    private int serialNumber;

    public Subject() {}

    public Subject(String name, String homework, int serialNumber) {
        this.name = name;
        this.homework = homework;
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
