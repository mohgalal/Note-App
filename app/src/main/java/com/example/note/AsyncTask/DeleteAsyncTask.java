package com.example.note.AsyncTask;

import android.os.AsyncTask;

import com.example.note.Note;
import com.example.note.NotesDao;

public class DeleteAsyncTask extends AsyncTask<Note,Void,Void> {

    private NotesDao dnotesDao;

    public DeleteAsyncTask(NotesDao dnotesDao) {
        this.dnotesDao = dnotesDao;
    }

    @Override
    protected Void doInBackground(Note... notes) {

        dnotesDao.deletenote(notes[0]);

        return null;
    }
}
