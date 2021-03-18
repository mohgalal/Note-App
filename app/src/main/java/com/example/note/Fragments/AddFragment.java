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

import com.example.note.AsyncTask.InsertAsyncTask;
import com.example.note.Note;
import com.example.note.NoteDatabase;
import com.example.note.R;

public class AddFragment extends Fragment {

    EditText titleEd , descriptionEd;
    Button saveBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_add, container,false);

        titleEd = view.findViewById(R.id.title_et);
        descriptionEd = view.findViewById(R.id.description_et);
        saveBtn = view.findViewById(R.id.save_btn);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpClickListener();
    }

    private void setUpClickListener() {

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEd.getText().toString();
                String description = descriptionEd.getText().toString();

                if (title.isEmpty() || description.isEmpty()){

                    Toast.makeText(getContext(), "There is empty fields", Toast.LENGTH_SHORT).show();
                }
                else {

                    // TODO : I will make insert function here

                    new InsertAsyncTask(NoteDatabase.getRoomObj(requireContext())
                            .getnoteDao()).execute(new Note(title,description));

                    Navigation.findNavController(v).navigate(R.id.action_addFragment_to_homeFragment);
                }
            }
        });
    }
}