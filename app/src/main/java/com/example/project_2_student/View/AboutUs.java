package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project_2_student.R;

public class AboutUs extends AppCompatActivity {

    DrawerLayout drawerLayout;
    SharedPreferences sharedPreferences;
    TextView num_notification , name_tool_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        init();



    }//End of onCreate


    public void ClickAbsenceWarning(View view)
    {
        finish();
        MainParent.redirectActivity(this , Absence_Warning.class);
    }
    public void init()
    {
        name_tool_bar = findViewById(R.id.main_toolbar_activity_name_tv) ;
        name_tool_bar.setText(R.string.About_Us);

        sharedPreferences = getSharedPreferences(LoginActivity.STUDENT_DATA_DB,MODE_PRIVATE);
        num_notification = findViewById(R.id.num_notification);
        if(!sharedPreferences.getString(LoginActivity.NUM_NOTIFICATION,"").equals("0")) {
            num_notification.setVisibility(View.VISIBLE);
            num_notification.setText(sharedPreferences.getString(LoginActivity.NUM_NOTIFICATION, ""));
        }
        drawerLayout = findViewById(R.id.about_us_drawer_layout);
    }//End of init


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