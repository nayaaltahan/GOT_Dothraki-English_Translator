package com.example.got.db;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.got.model.Translate;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TranslateDao {

    @Insert
    void insert(Translate note);

    @Update
    void update(Translate note);

    @Delete
    void delete(Translate note);

    @Query("DELETE FROM translates_table")
    void deleteAllNotes();

    @Query("SELECT * FROM translates_table ORDER BY date DESC")
    LiveData<List<Translate>> getAllNotes();

}
