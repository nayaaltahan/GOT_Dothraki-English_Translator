package com.example.got.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.got.util.Converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity(tableName = "translates_table")
public class Translate implements Comparable<Translate> {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String english;
    private String dothraki;
    public long date;


    public Translate(String english, String dothraki, LocalDateTime date) {
        this.english = english;
        this.dothraki = dothraki;
        this.date = Converter.convertToLong(date);
    }

    public Translate(String english, String dothraki) {
        this.english = english;
        this.dothraki = dothraki;
        this.date = Converter.convertToLong(LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getDothraki() {
        return dothraki;
    }

    public void setDothraki(String dothraki) {
        this.dothraki = dothraki;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public int compareTo(Translate translate) {
        if(date > translate.date){
            return -1;
        }else return 1;
    }


}
