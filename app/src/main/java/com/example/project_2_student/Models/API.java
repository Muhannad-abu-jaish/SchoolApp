package com.example.project_2_student.Models;

import com.example.project_2_student.View.GeneralNote;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {
    @POST("/login")
    public Call<Student> loginStudent(@Body  DataLogin dataLogin);
    @POST("/instructor/login")
    public Call<Instructors> loginInstructor(@Body DataLogin dataLogin);
    @GET("/show-public-note")
    public Call<ArrayList<GeneralNotes>> GENERAL_NOTES_CALL(@Header("Authorization") String token);
    @GET("/show-private-note")
    public Call<ArrayList<PrivateNotes>> PRIVATE_NOTES_CALL(@Header("Authorization") String token) ;
    }
