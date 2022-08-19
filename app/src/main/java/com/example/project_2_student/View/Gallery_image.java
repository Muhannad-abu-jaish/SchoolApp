package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project_2_student.Controller.AdapterGalleryImage;
import com.example.project_2_student.Models.IMAGES;
import com.example.project_2_student.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Gallery_image extends AppCompatActivity {
RecyclerView recyclerView;
AdapterGalleryImage adapterGalleryImage;
ArrayList<IMAGES>imagesArrayList;
    Drawable drawable;
    SharedPreferences sharedPreferences;
    TextView num_notification , name_tool_bar;
    DrawerLayout drawerLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_image);
        init();
        SetImageInArrayList();
        setAdapter();
    }

    private void setAdapter() {
    adapterGalleryImage.setImages(imagesArrayList);
    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.HORIZONTAL,false));
    recyclerView.setAdapter(adapterGalleryImage);
    }

    public void ClickAbsenceWarning(View view)
    {
        finish();
        MainParent.redirectActivity(this , Absence_Warning.class);
    }

    private void SetImageInArrayList() {
         drawable = getDrawable(R.drawable.image_saadeh_1);
        imagesArrayList.add(new IMAGES(drawable));
         drawable = getDrawable(R.drawable.image_saadeh_2);
        imagesArrayList.add(new IMAGES(drawable));
        drawable = getDrawable(R.drawable.image_saadeh_3);
        imagesArrayList.add(new IMAGES(drawable));
        drawable = getDrawable(R.drawable.image_saadeh_4);
        imagesArrayList.add(new IMAGES(drawable));
        drawable = getDrawable(R.drawable.image_saadeh_5);
        imagesArrayList.add(new IMAGES(drawable));
        drawable = getDrawable(R.drawable.image_saadeh_6);
        imagesArrayList.add(new IMAGES(drawable));
    }
    private void init(){
        drawerLayout = findViewById(R.id.gallery_drawer_layout) ;
        name_tool_bar = findViewById(R.id.main_toolbar_activity_name_tv) ;
        name_tool_bar.setText(R.string.GALLERY_IMAGE);
        recyclerView = findViewById(R.id.recycler_gallery_image);
        adapterGalleryImage = new AdapterGalleryImage();
        imagesArrayList = new ArrayList<>();
        sharedPreferences = getSharedPreferences(LoginActivity.STUDENT_DATA_DB,MODE_PRIVATE);
        num_notification = findViewById(R.id.num_notification);
        if(!sharedPreferences.getString(LoginActivity.NUM_NOTIFICATION,"").equals("0")){
            num_notification.setVisibility(View.VISIBLE);
            num_notification.setText(sharedPreferences.getString(LoginActivity.NUM_NOTIFICATION,""));
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
        finish();
        MainParent.redirectActivity(this , MainParent.class);
    }//End of ClickHome
    public void ClickGallery(View view){
        finish();
       recreate();
    }
    public void ClickContactUs(View view){
        finish();
        MainParent.redirectActivity(this,Contact_us.class);
    }

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