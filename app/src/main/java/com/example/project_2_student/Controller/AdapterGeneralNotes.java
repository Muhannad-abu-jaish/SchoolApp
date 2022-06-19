package com.example.project_2_student.Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_student.Models.GeneralNotes;
import com.example.project_2_student.R;

import java.util.ArrayList;

public class AdapterGeneralNotes extends RecyclerView.Adapter<AdapterGeneralNotes.ViewHolder> {
    ArrayList<GeneralNotes> generalNotes = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.public_adverts_card_view,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(""+generalNotes.get(position).getTitle());
        holder.exp_date.setText(""+generalNotes.get(position).getExp_date());
        holder.adverts_text.setText(""+generalNotes.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return generalNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title , adverts_text , exp_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_adverts);
            adverts_text = itemView.findViewById(R.id.Text_adverts);
            exp_date = itemView.findViewById(R.id.Date_exp_adverts);
        }
    }

    public ArrayList<GeneralNotes> getGeneralNotes() {
        return generalNotes;
    }

    public void setGeneralNotes(ArrayList<GeneralNotes> generalNotes) {
        this.generalNotes = generalNotes;
        System.out.println("Message : " + generalNotes.get(0).getMessage());
    }
}
