package com.example.note.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.note.AsyncTask.DeleteAsyncTask;
import com.example.note.AsyncTask.GetNotesAsyncTask;
import com.example.note.Note;
import com.example.note.NoteDatabase;
import com.example.note.NotesRvAdapter;
import com.example.note.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeFragment extends Fragment {

   private RecyclerView noteRv;
   private NotesRvAdapter noteAdapter;
   private List<Note> noteList = new ArrayList<>();

   FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        noteRv=view.findViewById(R.id.note_rv);
        fab=view.findViewById(R.id.fab_f);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getAllNotes();
        setUpRecyclerView();
        setUpClickListener();
    }



    private void  setUpClickListener(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_addFragment);
            }
        });


    }

    private void setUpRecyclerView() {

        noteAdapter=new NotesRvAdapter(noteList, new NotesRvAdapter.onItemClickListener() {
            @Override
            public void onItemClick(final View view, final int position) {

                setUpEditAndDeleteDialog(view, position);

            }
        });
        noteRv.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false));
        noteRv.addItemDecoration(new DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL));
        noteRv.setAdapter(noteAdapter);
    }

    private void setUpEditAndDeleteDialog(final View view, final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setMessage("Do you want to delete or edit??");
        dialog.setNegativeButton("delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO:here i will make the delete function

                deleteNote(position);
            }
        });

        dialog.setPositiveButton("edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO:here i will make the edit function

                getSelectedNoteAndNavgateToEdit(position, view);


            }
        });
        dialog.show();
    }

    private void getSelectedNoteAndNavgateToEdit(int position, View view) {
        Note selectedNote = noteList.get(position);
        Bundle bundle = new Bundle();
        bundle.putSerializable("note",selectedNote);

        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_editFragment,bundle);
    }

    private void deleteNote(int position) {
        Note selectedNote = noteList.get(position);
        new DeleteAsyncTask(NoteDatabase.getRoomObj(requireContext()).getnoteDao()).execute(selectedNote);
        // السطر ده بيعمل ريفريش للداتا في الداتابيز من التغير الي حصل
        noteAdapter.notifyDataSetChanged();
        // السطر ده بيرجعلي النوتز بعد ماحصلها ريفريش
        getAllNotes();
    }

    private void getAllNotes() {
        noteList.clear();

        try {
            noteList.addAll(new GetNotesAsyncTask(NoteDatabase.getRoomObj(requireContext())
                    .getnoteDao()).execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}