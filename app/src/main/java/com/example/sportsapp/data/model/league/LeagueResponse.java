package com.example.sportsapp.data.model.league;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class LeagueResponse {
    private int total_count;

    @SerializedName("result")
    @Expose()
    private List<LeagueModel> leagues;

    public int getTotal_count(){
        return total_count;
    }

    public List<LeagueModel> getLeagues(){
        return leagues;
    }


    @Override
    public String toString() {
        return "LeagueResponse{" +
                "total_count=" + total_count +
                ", leagues=" + leagues +
                '}';
    }
}
