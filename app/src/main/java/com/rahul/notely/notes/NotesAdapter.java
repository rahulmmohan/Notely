package com.rahul.notely.notes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.rahul.notely.R;
import com.rahul.notely.data.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private List<Note> notesList;
    private NoteItemListener noteItemListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date, detail;
        public CheckBox makeHearted, makeFavourite;
        public View deleteNote;

        public MyViewHolder(View view) {
            super(view);
            deleteNote= view.findViewById(R.id.deleteNote);
            title = view.findViewById(R.id.noteTitle);
            date = view.findViewById(R.id.noteDate);
            detail = view.findViewById(R.id.noteDescription);
            makeHearted = view.findViewById(R.id.makeHearted);
            makeFavourite = view.findViewById(R.id.makeFavourite);
        }
    }


    public NotesAdapter(NoteItemListener noteItemListener,List<Note> notesList) {
        this.noteItemListener = noteItemListener;
        this.notesList = notesList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notes_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Note note = notesList.get(position);
        holder.title.setText(note.getTitle());
        holder.date.setText(note.msToDate());
        holder.makeFavourite.setChecked(note.getFavourite());
        holder.makeHearted.setChecked(note.getHearted());
        holder.makeFavourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                noteItemListener.makeNoteFavourite(note,checked);
            }
        });
        holder.makeHearted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                noteItemListener.makeNoteHearted(note,checked);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteItemListener.onNoteClick(note);
            }
        });
        holder.deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteItemListener.onNoteDelete(note);
            }
        });

    }


    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public interface NoteItemListener {

        void onNoteClick(Note clickedTask);

        void onNoteDelete(Note clickedTask);

        void makeNoteHearted(Note note, boolean hearted);

        void makeNoteFavourite(Note note, boolean stared);

    }
}