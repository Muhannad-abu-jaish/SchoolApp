package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project_2_student.R;

public class Contact_us extends AppCompatActivity {
 DrawerLayout drawerLayout ;
 LinearLayout facebook , twitter , website;
 TextView num_notification;
 SharedPreferences sharedPreferences;
 TextView name_tool_bar;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        init();
        clickingLinkFacebook();
        clickingLinkTwitter();
        clickingLinkWebsite();
    }

    private void clickingLinkWebsite() {
    website.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.saadeschool.com/ar/index.php"));
            startActivity(intent);
        }
    });
    }

    private void clickingLinkTwitter() {
    twitter.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://twitter.com/saadeschool"));
            startActivity(intent);
        }
    });
    }

    private void clickingLinkFacebook() {
    facebook.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/Alsaade.private.School"));
            startActivity(intent);
        }
    });
    }

    private void init(){
        drawerLayout = findViewById(R.id.drawer_contact_us);

        name_tool_bar = findViewById(R.id.main_toolbar_activity_name_tv) ;
        name_tool_bar.setText(R.string.CONTUCT_US);

        facebook = findViewById(R.id.link_facebook);
        twitter = findViewById(R.id.link_twitter);
        website = findViewById(R.id.link_website);
        sharedPreferences = getSharedPreferences(LoginActivity.STUDENT_DATA_DB,MODE_PRIVATE);
        num_notification = findViewById(R.id.num_notification);
        if(!sharedPreferences.getString(LoginActivity.NUM_NOTIFICATION,"").equals("0")){
            num_notification.setVisibility(View.VISIBLE);
            num_notification.setText(sharedPreferences.getString(LoginActivity.NUM_NOTIFICATION,""));
        }
    }
    public void ClickContactUs(View view){
      recreate();
    }
    public void ClickMenu(View view)
    {
        //Open Drawer
        openDrawer(drawerLayout);
    }

    public static void openDrawer(DrawerLayout drawerLayout)
    {
        //Open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void ClickGallery(View view){
        MainParent.redirectActivity(this,Gallery_image.class);
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
        MainParent.redirectActivity(this  , PersonalProfile.class );

    }//End of ClickDashboard



    //  يفترض أضيف الsettings و ال honorary board



    public void ClickAboutUs(View view)
    {

        //Redirect activity to about us
        MainParent.redirectActivity(this , AboutUs.class );

    }//End of ClickِAboutUs



    public void ClickLogOut(View view)
    {
        //Close app
        MainParent.logout(this);

    }//End of ClickِAboutUs

}