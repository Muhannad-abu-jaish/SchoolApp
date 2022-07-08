package com.example.project_2_student.Models;

public class PrivateNotes {

    private int id;
    private String message;
    private String start_date;
    public PrivateNotes(int id , String message , String start_date  )
    {
        this.id = id;
        this.message = message;
        this.start_date = start_date;

    }

    public PrivateNotes() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }
}
