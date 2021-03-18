package com.example.note;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class,version = 1,exportSchema = false)
public abstract  class NoteDatabase extends RoomDatabase {

    public  abstract NotesDao getnoteDao();

    //singleton
    private static NoteDatabase databaseObj;

    public static synchronized NoteDatabase getRoomObj(Context context){

        if (databaseObj == null){

            databaseObj = Room.databaseBuilder(context,NoteDatabase.class,"note_db").build();
        }
        return databaseObj;
    }
}
