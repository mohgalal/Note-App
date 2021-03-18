package com.example.note.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.note.AsyncTask.updateAsyncTask;
import com.example.note.Note;
import com.example.note.NoteDatabase;
import com.example.note.R;

public class EditFragment extends Fragment {

    EditText editTitleEt,editDescriptionEt;
    Button updateBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_edit, container, false);

        editTitleEt=view.findViewById(R.id.edit_title_et);
        editDescriptionEt=view.findViewById(R.id.edit_description_et);
        updateBtn=view.findViewById(R.id.update_btn);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Note note = getNoteFromHomeFragment();
        setUpClickListener(note);

    }



    private Note getNoteFromHomeFragment() {

        Note selectedNote = null;
        Bundle bundle = getArguments();
        if (bundle != null){
            selectedNote = (Note) bundle.getSerializable("note");
            editTitleEt.setText(selectedNote.getTitleNote());
            editDescriptionEt.setText(selectedNote.getDescriptionNote());
        }
       return selectedNote;


    }

    private void setUpClickListener(final Note updatedNote) {


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = editTitleEt.getText().toString();
                String description = editDescriptionEt.getText().toString();

                if(title.isEmpty() || description.isEmpty()){

                    Toast.makeText(requireContext(), "There is an empty field!", Toast.LENGTH_SHORT).show();

                } else {

                    updatedNote.setTitleNote(title);
                    updatedNote.setDescriptionNote(description);
                    new updateAsyncTask(NoteDatabase.getRoomObj(requireContext()).getnoteDao()).execute(updatedNote);

                    Toast.makeText(requireContext(), "Note has been updated successfully!", Toast.LENGTH_SHORT).show();

                    Navigation.findNavController(view).navigate(R.id.action_editFragment_to_homeFragment);

                }

            }
        });


    }
}