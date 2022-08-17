package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project_2_student.R;

public class MainParent extends AppCompatActivity {
    DrawerLayout drawerLayout;
    LinearLayout main_parent_calendar , main_parent_general_notes , main_parent_special_notes ;
    LinearLayout main_parent_final_result , main_parent_sends_message ;
    TextView num_notification;
    SharedPreferences sharedPreferences;
    TextView name_tool_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_parent);


        init();
        clickOnGeneralNotes();
        clickOnSpecialNotes();
        clickOnCalendar();
        clickOnSendMessage();
        clickOnFinalResult();

    }//End of onCreate


    public void init()
    {
        drawerLayout = findViewById(R.id.main_parent_drawer_layout);

        name_tool_bar = findViewById(R.id.main_toolbar_activity_name_tv) ;
        name_tool_bar.setText(R.string.CreativityText);
        main_parent_calendar=findViewById(R.id.main_parent_ll2_ll1_ll1_ll1_calender) ;
        main_parent_general_notes=findViewById(R.id.main_parent_ll_ll1_ll1_ll1_general_notes) ;
        main_parent_special_notes=findViewById(R.id.main_parent_ll_ll1_ll2_ll2_special_notes) ;
        main_parent_final_result=findViewById(R.id.main_parent_ll_ll1_ll3_ll3_final_result) ;
        main_parent_sends_message=findViewById(R.id.main_parent_ll2_ll1_ll2_ll2_sends_message) ;
        num_notification = findViewById(R.id.num_notification);
        sharedPreferences = getSharedPreferences(LoginActivity.STUDENT_DATA_DB, MODE_PRIVATE);
        if(!sharedPreferences.getString(LoginActivity.NUM_NOTIFICATION,"").equals("0")){
            num_notification.setVisibility(View.VISIBLE);
            num_notification.setText(sharedPreferences.getString(LoginActivity.NUM_NOTIFICATION,""));
        }
    }//End of init

    public void clickOnCalendar()
    {
        main_parent_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               MainParent.redirectActivity(MainParent.this,WeekProgram.class);
            }
        });
    }


    public void clickOnGeneralNotes()
    {
        main_parent_general_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                redirectActivity(MainParent.this,GeneralNote.class);

            }
        });
    }


    public void clickOnSpecialNotes()
    {
        main_parent_special_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                redirectActivity(MainParent.this , PrivateNote.class);
            }
        });
    }



    public void clickOnFinalResult()
    {
        main_parent_final_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectActivity(MainParent.this,FinalResult.class);
            }
        });
    }


    public void clickOnSendMessage()
    {
        main_parent_sends_message.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                redirectActivity(MainParent.this , SendMessage.class);
            }
        });
    }

    public void ClickAbsenceWarning(View view){
        redirectActivity(this,Absence_Warning.class);
    }
    public void ClickContactUs(View view){
        redirectActivity(this,Contact_us.class);
    }
    public void ClickMenu(View view)
    {
        //Open Drawer
        openDrawer(drawerLayout);
    }
     public void ClickGallery(View view){
        redirectActivity(this,Gallery_image.class);
     }

    public static void openDrawer(DrawerLayout drawerLayout)
    {
        //Open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view)
    {
        //Close drawer
        closeDrawer(drawerLayout);
    }

    public static void closeDrawer(DrawerLayout drawerLayout)
    {
        System.out.println("Hello");
        //Close drawer layout
        //Check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            //When drawer is open
            //Close drawer
            drawerLayout.closeDrawer(GravityCompat.START);

        }

    }//End of closeDrawer

    public void ClickHome( View view)
    {
        //Recreate activity
        recreate();

    }//End of ClickHome


    public void ClickPersonalProfile(View view)//PersonalProfile
    {
        //Redirect activity to dashboard
        redirectActivity(this  , PersonalProfile.class );

    }//End of ClickDashboard



    //  يفترض أضيف الsettings و ال honorary board



    public void ClickAboutUs(View view)
    {

        //Redirect activity to about us
        redirectActivity(this , AboutUs.class );

    }//End of ClickِAboutUs



    public void ClickLogOut(View view)
    {
        System.out.println(" am in about from Main parent");
        //Close app
        logout(this);

    }//End of ClickِAboutUs

    public static void logout( final Activity activity)
    {
        //Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //Set title
        builder.setTitle("Logout");
        //Set message
        builder.setMessage("Are you sure yoy want to logout ?");
        //positive yes button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //Finish activity
                redirectActivity(activity,LoginActivity.class);
                activity.finishAffinity();
                //Exit app
                System.exit(0);
            }
        });
        //Negative no button
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss dialog
                dialog.dismiss();
            }
        });
        //Show dialog
        builder.show();
    }//End of logout


    public static void redirectActivity(Activity activity , Class aClass)
    {
        //Initialize intent
        Intent intent = new Intent(activity , aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start activity
        activity.startActivity(intent);

    }//End of redirectActivity


    @Override
    protected void onPause()
    {
        super.onPause();
        //Close drawer
        closeDrawer(drawerLayout);
    }
}