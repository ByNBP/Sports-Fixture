package com.example.sportsapp.data.model.league;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "leagues")
public class LeagueModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "league")
    public String league;

    @ColumnInfo(name = "league_key")
    public String key;

    public LeagueModel(String league, String key){
        this.league = league;
        this.key = key;
    }

    public String getLeague() {
        return league;
    }

    public String getKey() {
        return key;
    }

}
