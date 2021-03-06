package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.project_2_student.Constant.CONSTANT;
import com.example.project_2_student.Controller.AdapterGeneralNotes;
import com.example.project_2_student.Controller.AdapterPrivateNotes;
import com.example.project_2_student.Models.API;
import com.example.project_2_student.Models.GeneralNotes;
import com.example.project_2_student.Models.PrivateNotes;
import com.example.project_2_student.Models.PrivateNotesDB;
import com.example.project_2_student.R;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivateNote extends AppCompatActivity {


    ArrayList<PrivateNotes> privateNotes ;//
    RecyclerView recyclerView; //
    PrivateNotesDB privateNotesDB ;
    AdapterPrivateNotes adapterPrivateNotes;//
    DrawerLayout drawerLayout;
    String myToken;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_note);
        init();
        try {
            GET_ANNOUNCEMENT();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void GET_ANNOUNCEMENT() throws InterruptedException {
        API api = CONSTANT.CREATING_CALL();
        Call<ArrayList<PrivateNotes>> call = api.PRIVATE_NOTES_CALL(myToken);
        call.enqueue(new Callback<ArrayList<PrivateNotes>>() {
            @Override
            public void onResponse(Call<ArrayList<PrivateNotes>> call, Response<ArrayList<PrivateNotes>> response) {
                if(response.isSuccessful()){
                    adapterPrivateNotes.setPrivateNotes(response.body());
                    setAdapterPrivateNotes(adapterPrivateNotes);
                    addPrivateNoteToDataBase(response) ;

                }else{
                    try {
                        System.out.println("Error Statues !" + response.code() + "\t Error Body : " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PrivateNotes>> call, Throwable t) {
                System.out.println("Error : " + t.getMessage());
            }
        });
    }
    public void init(){
         privateNotesDB = new PrivateNotesDB(this);
        privateNotes = privateNotesDB.getAllPrivateNotes();
        recyclerView = findViewById(R.id.private_note_recycler_adverts);
        drawerLayout = findViewById(R.id.private_notes_drawer_layout);
        adapterPrivateNotes = new AdapterPrivateNotes(this , privateNotes);
        sharedPreferences = getSharedPreferences("StudentData",MODE_PRIVATE);
        myToken = sharedPreferences.getString(LoginActivity.TOKEN,"");

        System.out.println("Token = " + myToken) ;
        setAdapterPrivateNotes(adapterPrivateNotes) ;
    }

    public void addPrivateNoteToDataBase (Response<ArrayList<PrivateNotes>> response)
    {
        privateNotes = response.body();
        for (int i = 0 ;i<privateNotes.size() ; i++)
        {
            privateNotesDB.addNote(privateNotes.get(i)) ;
        }

    }

    public void setAdapterPrivateNotes(AdapterPrivateNotes adapter_adverts){
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
        this.finish();
    }//End of ClickHome


    public void ClickAboutUs(View view)
    {
        //Recreate activity
        MainParent.redirectActivity(this , AboutUs.class);
        this.finish();
    }//End of ClickAboutUs

    public void ClickPersonalProfile(View view)
    {
        //Redirect activity to dashboard
        MainParent.redirectActivity(this , PersonalProfile.class);
        this.finish();
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