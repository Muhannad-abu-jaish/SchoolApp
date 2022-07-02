package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.project_2_student.Constant.CONSTANT;
import com.example.project_2_student.Controller.AdapterGeneralNotes;
import com.example.project_2_student.Models.API;
import com.example.project_2_student.Models.GeneralNotes;
import com.example.project_2_student.R;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralNote extends AppCompatActivity  {

    ArrayList<GeneralNotes> public_adverts_models = new ArrayList<>();
    RecyclerView recyclerView;
    AdapterGeneralNotes adapter_adverts_public , adapter_adverts_private;
    DrawerLayout drawerLayout;
    String myToken;
    SharedPreferences sharedPreferences;

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
    }

    private void GET_ANNOUNCEMENT() throws InterruptedException {
        API api = CONSTANT.CREATING_CALL();
                Call<ArrayList<GeneralNotes>> call = api.GENERAL_NOTES_CALL(myToken);
                call.enqueue(new Callback<ArrayList<GeneralNotes>>() {
                    @Override
                    public void onResponse(Call<ArrayList<GeneralNotes>> call, Response<ArrayList<GeneralNotes>> response) {
                        if(response.isSuccessful()){
                            adapter_adverts_public.setGeneralNotes(response.body());
                            setAdapter(adapter_adverts_public);
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
                    }
                });
            }
    public void init(){
        recyclerView = findViewById(R.id.recycler_adverts);
        drawerLayout = findViewById(R.id.general_notes_drawer_layout);
        adapter_adverts_private = new AdapterGeneralNotes();
        adapter_adverts_public = new AdapterGeneralNotes();
        sharedPreferences = getSharedPreferences("StudentData",MODE_PRIVATE);
        myToken = sharedPreferences.getString(LoginActivity.TOKEN,"");
        System.out.println("Token = " + myToken);
    }
    public void setAdapter(AdapterGeneralNotes adapter_adverts){
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter_adverts);
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
        recreate();
    }//End of ClickAboutUs

    public void ClickPersonalProfile(View view)
    {
        //Redirect activity to dashboard
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