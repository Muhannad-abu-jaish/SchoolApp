package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.project_2_student.Constant.CONSTANT;
import com.example.project_2_student.Controller.AdapterAbsenceWarning;
import com.example.project_2_student.Models.API;
import com.example.project_2_student.Models.Absence;
import com.example.project_2_student.R;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Absence_Warning extends AppCompatActivity {
   AdapterAbsenceWarning adapterAbsenceWarning;
   RecyclerView recyclerView ;
   ArrayList<Absence> absences;
   SharedPreferences sharedPreferences;
   String token ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absence__warning);
        init();
        getData();
    }

    private void getData() {
     API api = CONSTANT.CREATING_CALL();
        Call<ArrayList<Absence>> arrayListCall = api.AbsenceWarning(token);
        arrayListCall.enqueue(new Callback<ArrayList<Absence>>() {
            @Override
            public void onResponse(Call<ArrayList<Absence>> call, Response<ArrayList<Absence>> response) {
                if(response.isSuccessful()){
                    setAdapter(response.body());
                }
                else{
                    try {
                        System.out.println("Error successfully : " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Absence>> call, Throwable t) {
                System.out.println("Error : " + t.getMessage());
            }
        });

    }

    private void setAdapter(ArrayList<Absence> body) {
    adapterAbsenceWarning.setAbsences(body);
    recyclerView.setAdapter(adapterAbsenceWarning);
    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
    }

    private  void init(){
        adapterAbsenceWarning = new AdapterAbsenceWarning();
        absences = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_absence_warning);
        sharedPreferences = getSharedPreferences(LoginActivity.STUDENT_DATA_DB,MODE_PRIVATE);
        token = sharedPreferences.getString(LoginActivity.TOKEN,"");
    }

}