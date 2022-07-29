package com.example.project_2_student.Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_student.Models.IMAGES;
import com.example.project_2_student.R;

import java.util.ArrayList;

public class AdapterGalleryImage extends RecyclerView.Adapter<AdapterGalleryImage.ViewHolder> {
    ArrayList<IMAGES>images = new ArrayList<>();

    public void setImages(ArrayList<IMAGES> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public AdapterGalleryImage.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_gallery,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGalleryImage.ViewHolder holder, int position) {
      holder.imageView.setImageDrawable(images.get(position).getDrawable());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_gallery);
        }
    }
}
