package com.example.project_2_student.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_student.Models.PrivateNotes;
import com.example.project_2_student.R;

import java.util.ArrayList;

public class AdapterMarksNote extends RecyclerView.Adapter<AdapterMarksNote.ViewHolderMarksNote> {




    ArrayList<PrivateNotes> marksNote = new ArrayList<>() ;
    LayoutInflater layoutInflater;
    public ArrayList<PrivateNotes> getMarksNote() {
        return marksNote;
    }

    public void setMarksNote(ArrayList<PrivateNotes> marksNote) {
        this.marksNote = marksNote;
    }

    public AdapterMarksNote (Context context , ArrayList<PrivateNotes> marksNote )
    {
        this.layoutInflater = LayoutInflater.from(context) ;
        this.marksNote = marksNote ;

    }
    @NonNull
    @Override
    public ViewHolderMarksNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mark_notes_card_view,parent,false);
        return new AdapterMarksNote.ViewHolderMarksNote(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMarksNote holder, int position) {


        String message = marksNote.get(position).getMessage();
        String start_date = marksNote.get(position).getStart_date();


        // Log.d("Title", "onBindViewHolder: Title -> " + message);

        holder.privateNoteMessage.setText(message);
        holder.privateNoteStartDate.setText(start_date);
    }

    @Override
    public int getItemCount() {
        return marksNote.size();
    }


    public class ViewHolderMarksNote extends RecyclerView.ViewHolder{

        TextView privateNoteSubject , privateNoteMessage , privateNoteStartDate , privateNoteAgoTime ;

        public ViewHolderMarksNote(@NonNull View itemView) {
            super(itemView);


            privateNoteMessage = itemView.findViewById(R.id.private_note_card_view_message_tv) ;
            //privateNoteTime = itemView.findViewById(R.id.private_note_card_view_time_tv) ; //time ago
            privateNoteStartDate = itemView.findViewById(R.id.private_note_card_view_receive_date_tv) ;

        }
    }
}
