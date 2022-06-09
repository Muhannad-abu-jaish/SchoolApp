package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_2_student.R;

public class PersonalProfile extends AppCompatActivity {


    DrawerLayout drawerLayout;


    ImageView personal_profile_move_back_iv , personal_profile_student_photo_iv;
    TextView personal_profile_student_name_tv , personal_profile_grade_number_tv , personal_profile_division_number_tv;
    TextView personal_profile_phone_number_tv , personal_profile_username_tv , personal_profile_password_tv , personal_profile_birth_date_tv ;
    TextView personal_profile_address_tv , personal_profile_admission_date_tv , personal_profile_age_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);

        init();



    }//End of onCreate

    public void init()
    {
        drawerLayout = findViewById(R.id.personal_profile_drawer_layout);

        personal_profile_move_back_iv =(ImageView) findViewById(R.id.personal_profile_ll_move_back);
        clickToBack();

        personal_profile_student_photo_iv=findViewById(R.id.personal_profile_ll2_student_photo_iv);
        personal_profile_student_name_tv=findViewById(R.id.personal_profile_ll2_student_name_tv);
        personal_profile_grade_number_tv=findViewById(R.id.personal_profile_ll2_cv_ll_ll_grade_number_tv);
        personal_profile_division_number_tv=findViewById(R.id.personal_profile_ll2_cv_ll2_division_number_tv);
        personal_profile_phone_number_tv=findViewById(R.id.personal_profile_rl_cv_ll_phone_number_tv);
        personal_profile_username_tv=findViewById(R.id.personal_profile_rl_cv_ll2_username_tv);
        personal_profile_password_tv=findViewById(R.id.personal_profile_rl_cv_ll3_password_tv);
        personal_profile_birth_date_tv=findViewById(R.id.personal_profile_rl_cv_ll4_birth_date_tv);
        personal_profile_address_tv=findViewById(R.id.personal_profile_rl_cv_ll5_address_tv);
        personal_profile_admission_date_tv=findViewById(R.id.personal_profile_rl_cv_ll6_admission_date_tv);
        personal_profile_age_tv=findViewById(R.id.personal_profile_rl_cv_ll7_age_tv);
    }

    public void clickToBack()
    {
        personal_profile_move_back_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //كود العودة للواجهة السابقة للبروفايل
            }
        });
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
        recreate();
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