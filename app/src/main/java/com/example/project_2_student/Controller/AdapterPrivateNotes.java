package com.example.project_2_student.Controller;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_student.Models.PrivateNotes;
import com.example.project_2_student.R;

import java.util.ArrayList;

public class AdapterPrivateNotes extends  RecyclerView.Adapter<AdapterPrivateNotes.ViewHolderPrivateNote> {

    LayoutInflater layoutInflater;
    ArrayList<PrivateNotes> privateNotes = new ArrayList<>();

    public ArrayList<PrivateNotes> getPrivateNotes() {
        return privateNotes;
    }

    public void setPrivateNotes(ArrayList<PrivateNotes> privateNotes) {
        this.privateNotes = privateNotes;
    }

    public AdapterPrivateNotes (Context context , ArrayList<PrivateNotes> privateNotes)
    {
        this.layoutInflater = LayoutInflater.from(context) ;
        this.privateNotes = privateNotes ;
    }


    @NonNull
    @Override
    public AdapterPrivateNotes.ViewHolderPrivateNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.privat_notes_card_view,parent,false);
        return new ViewHolderPrivateNote(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPrivateNote holder, int position) {


        String message = privateNotes.get(position).getMessage();
        String start_date = privateNotes.get(position).getStart_date();
        holder.privateNoteTime.setText(start_date);
        holder.privateNoteMessage.setText(message);
        holder.privateNoteMessage.setMovementMethod(new ScrollingMovementMethod());
    }

    @Override
    public int getItemCount() {
        return privateNotes.size();
    }


    public class ViewHolderPrivateNote extends RecyclerView.ViewHolder{

        TextView  privateNoteMessage  , privateNoteTime ;

        public ViewHolderPrivateNote(@NonNull View itemView) {
            super(itemView);

             privateNoteMessage = itemView.findViewById(R.id.private_note_card_view_message_tv) ;
            privateNoteTime = itemView.findViewById(R.id.private_note_card_view_time_tv) ;
        }
    }
}
