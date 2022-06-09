package com.example.project_2_student.Models;

public class GeneralNotes {

    String message, exp_date ,title;

    public GeneralNotes(String message, String exp_date, String title) {
        this.message = message;
        this.exp_date = exp_date;
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public String getExp_date() {
        return exp_date;
    }

    public String getTitle() {
        return title;
    }
}
