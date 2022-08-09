package com.example.project_2_student.Models;

public class Absence {
    String message , exp_date , start_date;

    public Absence(String message, String exp_date, String start_date) {
        this.message = message;
        this.exp_date = exp_date;
        this.start_date = start_date;
    }

    public String getMessage() {
        return message;
    }

    public String getExp_date() {
        return exp_date;
    }

    public String getStart_date() {
        return start_date;
    }
}
