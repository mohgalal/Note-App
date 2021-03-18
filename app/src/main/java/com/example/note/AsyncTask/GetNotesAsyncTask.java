package com.example.note.AsyncTask;


import android.os.AsyncTask;

import com.example.note.Note;
import com.example.note.NotesDao;

import java.util.List;

public class GetNotesAsyncTask extends AsyncTask<Void,Void, List<Note>> {

    private NotesDao gnotesDao;

    public GetNotesAsyncTask(NotesDao gnotesDao) {
        this.gnotesDao = gnotesDao;
    }


    @Override
    protected List<Note> doInBackground(Void... voids) {
        return gnotesDao.getAllNotes();
    }
}
