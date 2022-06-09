package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.project_2_student.Constant.CONSTANT;
import com.example.project_2_student.Controller.AdapterGeneralNotes;
import com.example.project_2_student.Models.API;
import com.example.project_2_student.Models.GeneralNotes;
import com.example.project_2_student.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralNote extends AppCompatActivity {

    ArrayList<GeneralNotes> public_adverts_models = new ArrayList<>();
    RecyclerView recyclerView;
    AdapterGeneralNotes adapter_adverts;
    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_note);
        init();
        GET_ANNOUNCEMENT();
    }

    private void GET_ANNOUNCEMENT()
    {
        API api = CONSTANT.CREATING_CALL();
        Call<ArrayList<GeneralNotes>> call = api.GENERAL_NOTES_CALL();
        call.enqueue(new Callback<ArrayList<GeneralNotes>>() {
            @Override
            public void onResponse(Call<ArrayList<GeneralNotes>> call, Response<ArrayList<GeneralNotes>> response) {
                if(response.isSuccessful()){
                    System.out.println("Exp _ Date : " + response.body().get(0).getExp_date());
                    adapter_adverts.setGeneralNotes(response.body());
                    setAdapter();
                }else{
                    System.out.println("Error Statues !" + response.code() + "\t Errpr Body : " + response.errorBody());
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
        adapter_adverts = new AdapterGeneralNotes();

    }
    public void setAdapter(){
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