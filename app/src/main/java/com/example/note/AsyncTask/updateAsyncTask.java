package com.example.note.AsyncTask;

import android.os.AsyncTask;

import com.example.note.Note;
import com.example.note.NotesDao;

public class updateAsyncTask extends AsyncTask<Note,Void,Void> {

    private NotesDao unotesDao;

    public updateAsyncTask(NotesDao unotesDao) {
        this.unotesDao = unotesDao;
    }

    @Override
    protected Void doInBackground(Note... notes) {

        unotesDao.updateNote(notes[0]);

        return null;
    }
}
