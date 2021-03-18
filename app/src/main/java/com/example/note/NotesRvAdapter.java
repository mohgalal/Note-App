package com.example.note;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesRvAdapter extends RecyclerView.Adapter<NotesRvAdapter.NotesViewHolder> {


    List<Note> noteList;
    onItemClickListener onItemClickListener;
// الانترفيس دي عشان اعمل بيها الدايالوج الي هيظهر من كل عنصر
    public interface onItemClickListener{

         void onItemClick(View view, int position);
    }

    public NotesRvAdapter(List<Note> noteList, NotesRvAdapter.onItemClickListener onItemClickListener) {
        this.noteList = noteList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_rv_design, parent,false);

        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotesViewHolder holder, final int position) {

        Note note = noteList.get(position);
        holder.titleTv.setText(note.getTitleNote());
        holder.descriptionTv.setText(note.getDescriptionNote());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v , holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv, descriptionTv;


        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.title_tv);
            descriptionTv = itemView.findViewById(R.id.description_tv);
        }
    }
}
