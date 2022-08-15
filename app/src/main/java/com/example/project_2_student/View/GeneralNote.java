package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_2_student.Constant.CONSTANT;
import com.example.project_2_student.Controller.AdapterGeneralNotes;
import com.example.project_2_student.Models.API;
import com.example.project_2_student.Models.GeneralNotes;
import com.example.project_2_student.Models.GeneralNotesDB;
import com.example.project_2_student.R;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralNote extends AppCompatActivity  {

    ArrayList<GeneralNotes> public_adverts_models;
    RecyclerView recyclerView;
    AdapterGeneralNotes adapter_adverts_public ;
    GeneralNotesDB generalNotesDB ;
    DrawerLayout drawerLayout;
    String myToken;
    SharedPreferences sharedPreferences;
    TextView num_notification;
    Button Retry;
    View noConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_note);
        init();
        try {
            GET_ANNOUNCEMENT();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        RetryConnection();
    }
    private void RetryConnection(){
        Retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noConnection.setVisibility(View.GONE);
                try {
                    GET_ANNOUNCEMENT();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void GET_ANNOUNCEMENT() throws InterruptedException {
        API api = CONSTANT.CREATING_CALL();
                Call<ArrayList<GeneralNotes>> call = api.GENERAL_NOTES_CALL(myToken);
                call.enqueue(new Callback<ArrayList<GeneralNotes>>() {
                    @Override
                    public void onResponse(Call<ArrayList<GeneralNotes>> call, Response<ArrayList<GeneralNotes>> response) {
                        if(response.isSuccessful()){
                            if(response.body().size()==0){
                                noConnection.setVisibility(View.VISIBLE);
                            }
                            else {
                                adapter_adverts_public.setGeneralNotes(response.body());
                                setAdapterGeneralNote(adapter_adverts_public);
                            }
                        }else{
                            try {
                                System.out.println("Error Statues !" + response.code() + "\t Error Body : " + response.errorBody().string());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ArrayList<GeneralNotes>> call, Throwable t) {
                        System.out.println("Error : " + t.getMessage());
                        noConnection.setVisibility(View.VISIBLE);
                    }
                });
            }

    public void init(){
        Retry = findViewById(R.id.retry_connection);
        noConnection = findViewById(R.id.view_NoConnection);
        recyclerView = findViewById(R.id.recycler_adverts);
        drawerLayout = findViewById(R.id.general_notes_drawer_layout);
        generalNotesDB = new GeneralNotesDB(this) ;
        public_adverts_models = generalNotesDB.getAllGeneralNotes() ;
        adapter_adverts_public = new AdapterGeneralNotes();
        sharedPreferences = getSharedPreferences(LoginActivity.STUDENT_DATA_DB,MODE_PRIVATE);
        myToken = sharedPreferences.getString(LoginActivity.TOKEN,"");
        num_notification = findViewById(R.id.num_notification);
        if(!sharedPreferences.getString(LoginActivity.NUM_NOTIFICATION,"").equals("0")){
            num_notification.setVisibility(View.VISIBLE);
            num_notification.setText(sharedPreferences.getString(LoginActivity.NUM_NOTIFICATION,""));
        }
    }

    public void setAdapterGeneralNote(AdapterGeneralNotes adapter_adverts){
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter_adverts);
    }

    public void addGeneralNoteToDataBase (Response<ArrayList<GeneralNotes>> response)
    {
        public_adverts_models = response.body();
        for (int i = 0 ;i<public_adverts_models.size() ; i++)
        {
            generalNotesDB.addGeneralNote(public_adverts_models.get(i)) ;
        }

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
        MainParent.redirectActivity(this , MainParent.class);
    }//End of ClickHome


    public void ClickAboutUs(View view)
    {
        //Recreate activity
        MainParent.redirectActivity(this , AboutUs.class);
    }//End of ClickAboutUs

    public void ClickPersonalProfile(View view)
    {
        //Redirect activity to dashboard
        MainParent.redirectActivity(this , PersonalProfile.class);
    }//End of ClickDashboard
    public void ClickGallery(View view){
        MainParent.redirectActivity(this,Gallery_image.class);
    }
    public void ClickContactUs(View view){
        MainParent.redirectActivity(this,Contact_us.class);
    }
    public void ClickLogOut(View view)
    {
        //Close app
        MainParent.logout(this);
    }//End of ClickLogout


    @Override
    protected void onPause() {
        MainParent.closeDrawer(drawerLayout);
        super.onPause();
        }
}