package com.example.schooldiary.model;

public class News {
    private String title;
    private String text;
    private String photo;
    private int serialNumber;

    public News() {
    }

    public News(String title, String text, String photo, int serialNumber) {
        this.title = title;
        this.text = text;
        this.photo = photo;
        this.serialNumber = serialNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
