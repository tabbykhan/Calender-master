package com.ajaygaikwad.calender2020.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ajaygaikwad.calender2020.Pojo.NotesDBpojo;

import java.util.List;

@Dao
public interface NotesDAO {

    @Insert
    public  void insert(NotesDBpojo... notesDBpojos);

    @Update
    void udpade(NotesDBpojo... notesDBpojos);

    @Delete
    void delete(NotesDBpojo... notesDBpojos);

    @Query("SELECT notes FROM  notesTable")
    public List<String> getNotes();
}
