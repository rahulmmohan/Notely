package com.rahul.notely.notes;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.rahul.notely.R;
import com.rahul.notely.data.Note;
import com.rahul.notely.widgets.swipereveal.RevealAdapterHelper;
import com.rahul.notely.widgets.swipereveal.SwipeRevealLayout;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private List<Note> notesList= new ArrayList<>();
    private NoteItemListener noteItemListener;
    private final RevealAdapterHelper mSwipeBinderHelper = new RevealAdapterHelper();


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, detail;
        public TextView date;
        public CheckBox makeHearted, makeFavourite;
        public View deleteNote,listItemView;
        SwipeRevealLayout swipeReveal;

        public MyViewHolder(View view) {
            super(view);
            swipeReveal  =  view.findViewById(R.id.revealDelete);
            listItemView  =  view.findViewById(R.id.listItemView);
            deleteNote= view.findViewById(R.id.deleteNote);
            title = view.findViewById(R.id.noteTitle);
            date = view.findViewById(R.id.noteDate);
            detail = view.findViewById(R.id.noteDescription);
            makeHearted = view.findViewById(R.id.makeHearted);
            makeFavourite = view.findViewById(R.id.makeFavourite);

            makeFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Note note = notesList.get(getAdapterPosition());
                    note.setFavourite(makeFavourite.isChecked());
                    noteItemListener.makeNoteFavourite(note,makeFavourite.isChecked());
                }
            });
            makeHearted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Note note = notesList.get(getAdapterPosition());
                    note.setHearted(makeHearted.isChecked());
                    noteItemListener.makeNoteHearted(note,makeHearted.isChecked());
                }
            });
        }
    }


    public NotesAdapter(NoteItemListener noteItemListener) {
        this.noteItemListener = noteItemListener;
        mSwipeBinderHelper.setOpenMode(RevealAdapterHelper.OpenMode.SINGLE);
    }

    public void replaceData(List<Note> notes) {
        setList(notes);
        notifyDataSetChanged();
    }
    public void removeAt(int position) {
        this.notesList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
    private void setList(List<Note> notes) {
        this.notesList = notes;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notes_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Note note = notesList.get(position);
        holder.title.setText(note.getTitle());
        holder.date.setText(DateUtils.getRelativeDateTimeString(holder.date.getContext(),note.getDate(),
                DateUtils.DAY_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_ABBREV_TIME)
                .toString().replace(","," at"));
        holder.makeFavourite.setChecked(note.getFavourite());
        holder.makeHearted.setChecked(note.getHearted());
        mSwipeBinderHelper.bind(position, holder.swipeReveal);
        holder.swipeReveal.setOpen(false,false);

        holder.listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteItemListener.onNoteClick(note);
            }
        });
        holder.deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noteItemListener.onNoteDelete(note,position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public interface NoteItemListener {

        void onNoteClick(Note clickedTask);

        void onNoteDelete(Note clickedTask,int position);

        void makeNoteHearted(Note note, boolean hearted);

        void makeNoteFavourite(Note note, boolean stared);

    }
}