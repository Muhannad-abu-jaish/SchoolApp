package com.example.project_2_student.Constant;


import com.example.project_2_student.Models.API;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CONSTANT {
    public static String URL = "http://192.168.113.12:3000/";
    public static String URL_EMULATOR = "http://192.168.187.104:3000/";
    public static API CREATING_CALL(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_EMULATOR)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        API api = retrofit.create(API.class);
        return api;
    }
}
