package com.example.project_2_student.Models;

import android.provider.ContactsContract;

import com.example.project_2_student.View.GeneralNote;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    @POST("/login")
    public Call<Student> loginStudent(@Body  DataLogin dataLogin);
    @POST("/instructor/login")
    public Call<Instructors> loginInstructor(@Body DataLogin dataLogin);
    @POST("/send_complaint")
    public Call<ResponseBody> SEND_MESSAGE_TO_INSTRUCTOR(@Header("Authorization") String token , @Body NoteTo noteToInstructor);
    @GET("/show-public-note")
    public Call<ArrayList<GeneralNotes>> GENERAL_NOTES_CALL(@Header("Authorization") String token);
    @GET("/show_week_program/{section}")
    public Call<ArrayList<Program>> show_week_program(@Path("section")int sectionId ) ;
    @GET("/show-private-note")
    public Call<ArrayList<PrivateNotes>> PRIVATE_NOTES_CALL(@Header("Authorization") String token) ;


    }
