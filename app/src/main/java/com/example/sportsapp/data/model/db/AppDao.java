package com.example.sportsapp.data.model.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sportsapp.data.model.league.LeagueModel;
import com.example.sportsapp.data.model.team.TeamModel;

import java.util.List;

@Dao
public interface AppDao {

    @Query("SELECT * FROM leagues")
    LiveData<List<LeagueModel>> getLeagues();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLeagues(List<LeagueModel> league);

    @Query("DELETE FROM leagues")
    void clearLeagues();

    @Query("SELECT * FROM teams WHERE team_key = :key")
    LiveData<List<TeamModel>> getTeams(String key);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTeams(List<TeamModel> team);

}
