package com.example.sportsapp.data.model.team;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "teams")
public class TeamModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "team")
    public String team;

    @ColumnInfo(name = "team_key")
    public String key ;

    public TeamModel(String team, String key){
        this.team = team;
        this.key = key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTeam() {
        return team;
    }

    public String getKey() {
        return key;
    }
}
