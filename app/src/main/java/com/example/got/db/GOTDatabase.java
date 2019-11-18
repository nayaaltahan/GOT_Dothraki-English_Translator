package com.example.got.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.got.model.Translate;

@Database(entities = {Translate.class}, version = 1,exportSchema = false)
public abstract class GOTDatabase extends RoomDatabase {

    private static GOTDatabase instance;

    public abstract TranslateDao translateDao();

    public static synchronized GOTDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    GOTDatabase.class, "got_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
