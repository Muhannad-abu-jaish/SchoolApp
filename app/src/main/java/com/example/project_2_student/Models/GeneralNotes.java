package com.example.project_2_student.Models;

public class GeneralNotes {

    private int id ;
    private String message, exp_date ,title;


    public GeneralNotes ()
    {

    }
    public GeneralNotes(int id, String message, String exp_date, String title) {
        this.id = id;
        this.message = message;
        this.exp_date = exp_date;
        this.title = title;
    }

    public GeneralNotes(String message, String exp_date, String title) {
        this.message = message;
        this.exp_date = exp_date;
        this.title = title;
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

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
