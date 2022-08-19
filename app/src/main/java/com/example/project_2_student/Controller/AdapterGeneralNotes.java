package com.example.project_2_student.Controller;

import android.text.method.ScrollingMovementMethod;
import android.content.Context;
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
    LayoutInflater layoutInflater;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.public_adverts_card_view,parent,false)
        );
    }

    public AdapterGeneralNotes(Context context , ArrayList<GeneralNotes> generalNotes) {
        this.generalNotes = generalNotes;
        this.layoutInflater = LayoutInflater.from(context) ;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(""+generalNotes.get(position).getTitle());
        holder.exp_date.setText(""+generalNotes.get(position).getExp_date());
        holder.adverts_text.setText(""+generalNotes.get(position).getMessage());
        holder.adverts_text.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public int getItemCount() {
        return generalNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title , adverts_text , exp_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.public_adverts_card_view_title_tv);
            adverts_text = itemView.findViewById(R.id.public_adverts_card_view_content_tv);
            exp_date = itemView.findViewById(R.id.public_adverts_card_view_time_tv);
        }
    }

    public ArrayList<GeneralNotes> getGeneralNotes() {
        return generalNotes;
    }

    public void setGeneralNotes(ArrayList<GeneralNotes> generalNotes) {
        this.generalNotes = generalNotes;

    }
}
