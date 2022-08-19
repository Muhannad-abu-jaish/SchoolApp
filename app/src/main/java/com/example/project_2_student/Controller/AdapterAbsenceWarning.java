package com.example.project_2_student.Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_student.Models.Absence;
import com.example.project_2_student.R;

import java.util.ArrayList;

public class AdapterAbsenceWarning extends RecyclerView.Adapter<AdapterAbsenceWarning.ViewHolder> {
    ArrayList<Absence> absences = new ArrayList<>();

    public void setAbsences(ArrayList<Absence> absences) {
        this.absences = absences;
    }

    @NonNull
    @Override
    public AdapterAbsenceWarning.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.cardview_abcense_warning,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAbsenceWarning.ViewHolder holder, int position) {
        holder.message.setText(absences.get(position).getMessage());
        holder.start_date.setText(absences.get(position).getStart_date());
        holder.exp_date.setText(absences.get(position).getExp_date());
    }

    @Override
    public int getItemCount() {
        return absences.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView message , start_date , exp_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.absence_warning_card_view_message_tv);
            start_date = itemView.findViewById(R.id.absence_warning_card_view_receive_date_tv);
            exp_date = itemView.findViewById(R.id.absence_warning_card_view_exp_date);
        }
    }
}
