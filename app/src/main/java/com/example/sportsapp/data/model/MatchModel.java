package com.example.sportsapp.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "match_model")
public class MatchModel {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "home")
    private String home;
    @ColumnInfo(name = "away")
    private String away;

    public MatchModel(String home, String away) {
        this.home = home;
        this.away = away;
    }

    public String getHome() {
        return home;
    }

    public String getAway() {
        return away;
    }
}
