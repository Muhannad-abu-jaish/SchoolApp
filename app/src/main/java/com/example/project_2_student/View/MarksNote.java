package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_2_student.Constant.CONSTANT;
import com.example.project_2_student.Controller.AdapterMarksNote;
import com.example.project_2_student.Controller.AdapterPrivateNotes;
import com.example.project_2_student.Models.API;
import com.example.project_2_student.Models.MarksNoteDB;
import com.example.project_2_student.Models.PrivateNotes;
import com.example.project_2_student.Models.PrivateNotesDB;
import com.example.project_2_student.R;
import com.example.project_2_student.View.AboutUs;
import com.example.project_2_student.View.Absence_Warning;
import com.example.project_2_student.View.Contact_us;
import com.example.project_2_student.View.Gallery_image;
import com.example.project_2_student.View.LoginActivity;
import com.example.project_2_student.View.MainParent;
import com.example.project_2_student.View.PersonalProfile;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarksNote extends AppCompatActivity {


    ArrayList<PrivateNotes> privateNotes ;//
    RecyclerView recyclerView; //
   // MarksNoteDB marksNoteDB ;
    AdapterMarksNote adapterMarksNote;//
    DrawerLayout drawerLayout;
    String myToken;
    SharedPreferences sharedPreferences;
    ProgressBar progressBar;

    TextView num_notification , name_tool_bar;

    Button Retry;
    View noConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks_note);

        init();
        try {
            GET_ANNOUNCEMENT();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Retry();
    }
    public void ClickAbsenceWarning(View view)
    {
        MainParent.redirectActivity(this , Absence_Warning.class);
    }

    public void init()
    {
        name_tool_bar = findViewById(R.id.main_toolbar_activity_name_tv) ;
        name_tool_bar.setText(R.string.MAIN_SPECIALNOTE);
        progressBar = findViewById(R.id.progress_marks);
    //    marksNoteDB = new MarksNoteDB(this);
        privateNotes = new ArrayList<>() ;
        Retry = findViewById(R.id.retry_connection);
        noConnection = findViewById(R.id.view_NoConnection);
        recyclerView = findViewById(R.id.marks_note_recycler_adverts);
        drawerLayout = findViewById(R.id.marks_note_drawer_layout);

        sharedPreferences = getSharedPreferences("StudentData",MODE_PRIVATE);
        myToken = sharedPreferences.getString(LoginActivity.TOKEN,"");

        System.out.println("Token = " + myToken) ;
    }
    public void Retry(){
        Retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noConnection.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
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
        Call<ArrayList<PrivateNotes>> call = api.MARKS_NOTES(myToken);
        call.enqueue(new Callback<ArrayList<PrivateNotes>>() {
            @Override
            public void onResponse(Call<ArrayList<PrivateNotes>> call, Response<ArrayList<PrivateNotes>> response) {
                if(response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    if (response.body().size()==0)
                    {
                        noConnection.setVisibility(View.VISIBLE);
                     //   marksNoteDB.deleteTable() ;
                    }else
                        {
                            adapterMarksNote = new AdapterMarksNote(getApplicationContext() , response.body());
                            setAdapterMarksNote(adapterMarksNote);
                          //  addPrivateNoteToDataBase(response) ;
                    }
                }else{
                    progressBar.setVisibility(View.GONE);
                    try {
                        Toast.makeText(getApplicationContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PrivateNotes>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                noConnection.setVisibility(View.VISIBLE);
                System.out.println("Error : " + t.getMessage());
            }
        });
    }

  /*  public void addPrivateNoteToDataBase (Response<ArrayList<PrivateNotes>> response)
    {
        privateNotes = response.body();
        for (int i = 0 ;i<privateNotes.size() ; i++)
        {
            boolean status = marksNoteDB.isExists(privateNotes.get(i).getId()) ;
            if (!status)
            marksNoteDB.addNote(privateNotes.get(i)) ;
        }

    }*/

    public void setAdapterMarksNote(AdapterMarksNote adapter_adverts){
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
        finish();
        MainParent.redirectActivity(this , MainParent.class);

    }//End of ClickHome


    public void ClickAboutUs(View view)
    {
        //Recreate activity
        finish();
        MainParent.redirectActivity(this , AboutUs.class);

    }//End of ClickAboutUs

    public void ClickPersonalProfile(View view)
    {
        //Redirect activity to dashboard
        finish();
        MainParent.redirectActivity(this , PersonalProfile.class);

    }//End of ClickDashboard
    public void ClickGallery(View view){

        finish();
        MainParent.redirectActivity(this, Gallery_image.class);
    }
    public void ClickContactUs(View view){
        finish();
        MainParent.redirectActivity(this, Contact_us.class);
    }
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