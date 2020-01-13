package com.ajaygaikwad.calender2020.db;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

import com.ajaygaikwad.calender2020.Pojo.NotesDBpojo;

@Database(entities = {NotesDBpojo.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase
{
    public abstract NotesDAO getNotesDAO();
}
