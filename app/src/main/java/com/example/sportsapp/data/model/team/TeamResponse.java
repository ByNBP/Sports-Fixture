package com.example.sportsapp.data.model.team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TeamResponse {
    private int total_count;

    @SerializedName("result")
    @Expose()
    private ArrayList<TeamModel> teams;

    public int getTotal_count(){
        return total_count;
    }

    public ArrayList<TeamModel> getTeams(){
        return teams;
    }


    @Override
    public String toString() {
        return "LeagueResponse{" +
                "total_count=" + total_count +
                ", leagues=" + teams +
                '}';
    }
}
