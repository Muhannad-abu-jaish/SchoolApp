package com.example.project_2_student.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.project_2_student.Controller.AdapterGalleryImage;
import com.example.project_2_student.Models.IMAGES;
import com.example.project_2_student.R;

import java.util.ArrayList;

public class Gallery_image extends AppCompatActivity {
RecyclerView recyclerView;
AdapterGalleryImage adapterGalleryImage;
ArrayList<IMAGES>imagesArrayList;
    Drawable drawable;
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
        recyclerView = findViewById(R.id.recycler_gallery_image);
        adapterGalleryImage = new AdapterGalleryImage();
        imagesArrayList = new ArrayList<>();

    }
}