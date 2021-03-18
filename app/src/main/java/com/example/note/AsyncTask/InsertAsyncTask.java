package com.example.note.AsyncTask;

import android.os.AsyncTask;

import com.example.note.Note;
import com.example.note.NotesDao;

public class InsertAsyncTask extends AsyncTask<Note,Void,Void> {

    private NotesDao inotesDao;

    public InsertAsyncTask(NotesDao notesDao) {
        this.inotesDao = notesDao;
    }

    @Override
    protected Void doInBackground(Note... notes) {

        inotesDao.insertNote(notes[0]);

        return null;
    }
}
