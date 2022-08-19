package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
    View noConnection;
    Button Retry;
   TextView name_tool_bar ;
   DrawerLayout drawerLayout ;
   ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absence__warning);
        init();
        getData();
        RetryConnection();
    }
    private void RetryConnection(){
        Retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                noConnection.setVisibility(View.GONE);
                getData();
            }
        });
    }
    private void getData() {
     API api = CONSTANT.CREATING_CALL();
        Call<ArrayList<Absence>> arrayListCall = api.AbsenceWarning(token);
        arrayListCall.enqueue(new Callback<ArrayList<Absence>>() {
            @Override
            public void onResponse(Call<ArrayList<Absence>> call, Response<ArrayList<Absence>> response) {
                if(response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    if(response.body().size()==0){
                        noConnection.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        setAdapter(response.body());
                    }
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    try {
                        Toast.makeText(getApplicationContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
                        System.out.println("Error successfully : " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Absence>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                System.out.println("Error : " + t.getMessage());
                noConnection.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setAdapter(ArrayList<Absence> body) {
    adapterAbsenceWarning.setAbsences(body);
    recyclerView.setAdapter(adapterAbsenceWarning);
    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
    }

    private  void init(){
        drawerLayout = findViewById(R.id.absence_warning_drawer_layout) ;
        Retry = findViewById(R.id.retry_connection);
        progressBar = findViewById(R.id.progress_absence_warning);
        noConnection = findViewById(R.id.view_NoConnection);
        name_tool_bar = findViewById(R.id.main_toolbar_activity_name_tv) ;
        name_tool_bar.setText(R.string.ABSENCE_WARNING);
        adapterAbsenceWarning = new AdapterAbsenceWarning();
        absences = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_absence_warning);
        sharedPreferences = getSharedPreferences(LoginActivity.STUDENT_DATA_DB,MODE_PRIVATE);
        token = sharedPreferences.getString(LoginActivity.TOKEN,"");
    }


    public void ClickAbsenceWarning(View view)
    {

    }
    public void ClickMenu(View view)
    {
        //Open drawer
        MainParent.openDrawer(drawerLayout);
    }//End of ClickMenu


    public void ClickLogo(View view)
    {
        //Close drawer
        MainParent.closeDrawer(drawerLayout);
    }//end of ClickLogo

    public void ClickHome(View view)
    {
        //Redirect activity to home
        finish();
        MainParent.redirectActivity(this , MainParent.class);
    }//End of ClickHome
    public void ClickGallery(View view){
        finish();
        MainParent.redirectActivity(this,Gallery_image.class);
    }
    public void ClickContactUs(View view){
        finish();
        MainParent.redirectActivity(this,Contact_us.class);
    }

    public void ClickAboutUs(View view)
    {
        //Recreate activity
        finish();
        recreate();
    }//End of ClickAboutUs

    public void ClickPersonalProfile(View view)
    {
        //Redirect activity to dashboard
        finish();
        MainParent.redirectActivity(this , PersonalProfile.class);
    }//End of ClickDashboard

    public void ClickLogOut(View view)
    {
        //Close app
        MainParent.logout(this);
    }//End of ClickLogout


    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        MainParent.closeDrawer(drawerLayout);
    }

}