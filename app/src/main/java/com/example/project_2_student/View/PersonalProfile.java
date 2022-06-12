package com.example.project_2_student.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project_2_student.R;

public class PersonalProfile extends AppCompatActivity {


    DrawerLayout drawerLayout;

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor;

    ImageView personal_profile_move_back_iv , personal_profile_student_photo_iv;
    TextView personal_profile_student_name_tv , personal_profile_grade_number_tv , personal_profile_division_number_tv;
    TextView personal_profile_phone_number_tv , personal_profile_username_tv , personal_profile_password_tv , personal_profile_birth_date_tv ;
    TextView personal_profile_address_tv , personal_profile_admission_date_tv , personal_profile_age_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);

        init();
        getPersonalInformation();



    }//End of onCreate


    public void init()
    {
        drawerLayout = findViewById(R.id.personal_profile_drawer_layout);

        sharedPreferences = getSharedPreferences("StudentData",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        personal_profile_move_back_iv =(ImageView) findViewById(R.id.personal_profile_ll_move_back);
        clickToBack();

        personal_profile_student_photo_iv=findViewById(R.id.personal_profile_ll2_student_photo_iv);
        personal_profile_student_name_tv=findViewById(R.id.personal_profile_ll2_student_name_tv);
        personal_profile_grade_number_tv=findViewById(R.id.personal_profile_ll2_cv_ll_ll_grade_number_tv);
        personal_profile_division_number_tv=findViewById(R.id.personal_profile_ll2_cv_ll2_division_number_tv);
        personal_profile_username_tv=findViewById(R.id.personal_profile_rl_cv_ll2_username_tv);
        personal_profile_password_tv=findViewById(R.id.personal_profile_rl_cv_ll3_password_tv);
        personal_profile_birth_date_tv=findViewById(R.id.personal_profile_rl_cv_ll4_birth_date_tv);
        personal_profile_address_tv=findViewById(R.id.personal_profile_rl_cv_ll5_address_tv);
        personal_profile_admission_date_tv=findViewById(R.id.personal_profile_rl_cv_ll6_admission_date_tv);
        personal_profile_age_tv=findViewById(R.id.personal_profile_rl_cv_ll7_age_tv);

    }//End on init


    public void getPersonalInformation()
    {
        String first_name = sharedPreferences.getString(LoginActivity.FIRST_NAME,"No First Name");
        String last_name = sharedPreferences.getString(LoginActivity.LAST_NAME,"No Last Name");
        String father_name = sharedPreferences.getString(LoginActivity.FATHER_NAME,"No Father Name");
        int age = sharedPreferences.getInt(LoginActivity.AGE,0);
        String user_name = sharedPreferences.getString(LoginActivity.USER_NAME,"No UserName");
        String password = sharedPreferences.getString(LoginActivity.PASSWORD,"No Password");
        String signin_date = sharedPreferences.getString(LoginActivity.SIGNIN_DATE,"No SignIn Date");
        String birth_date = sharedPreferences.getString(LoginActivity.BIRTH_DATE,"No Birth Date");
        int class_number = sharedPreferences.getInt(LoginActivity.NAME_CLASS,0);
        int section_number = sharedPreferences.getInt(LoginActivity.NAME_SEC,0);

        setOnProfile(first_name,father_name,last_name,age,user_name,password,signin_date,birth_date,class_number,section_number);

    }//End of getProfileInformation


    public void setOnProfile(String first_name ,String father_name,String last_name,int age,String user_name,String password,String signin_date,String birth_date,int class_number,int section_number )
    {

        String student_name = first_name + " " + father_name + " " + last_name;
        String ageString = String.valueOf(age);
        String class_numberString = String.valueOf(class_number);
        String section_numberString = String.valueOf(section_number);



        personal_profile_student_name_tv.setText(student_name);
        personal_profile_grade_number_tv.setText(class_numberString);
        personal_profile_division_number_tv.setText(section_numberString);
        personal_profile_username_tv.setText(user_name);
        personal_profile_password_tv.setText(password);
        personal_profile_birth_date_tv.setText(birth_date);
        personal_profile_admission_date_tv.setText(signin_date);
        personal_profile_age_tv.setText(ageString);


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