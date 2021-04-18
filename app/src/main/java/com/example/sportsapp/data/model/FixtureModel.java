package com.example.sportsapp.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "Fixture")
public class FixtureModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name ="ghost")
    public String ghostTeam;

    @ColumnInfo(name = "matches")
    public ArrayList<MatchModel> matches;

    public FixtureModel(String ghostTeam,ArrayList<MatchModel> matches) {
        this.ghostTeam = ghostTeam;
        this.matches = matches;
    }

    public FixtureModel(ArrayList<MatchModel> matches) {
        this.matches = matches;
    }

    public ArrayList<MatchModel> getMatches() {
        return matches;
    }

    public MatchModel getMatch(int index) {
        return matches.get(index);
    }

    public void setMatch(int index, MatchModel model) {
        matches.set(index, model);
    }

    public boolean hasGhostTeam(){
        return ghostTeam != null;
    }

    public String getGhostTeam() {
        return ghostTeam;
    }

}
