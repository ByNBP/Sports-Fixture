package com.example.sportsapp.ui.fragments.team;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sportsapp.data.model.league.LeagueModel;
import com.example.sportsapp.data.model.team.TeamModel;
import com.example.sportsapp.data.repository.LeagueRepository;
import com.example.sportsapp.data.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

public class TeamViewModel extends ViewModel {

    public LiveData<List<TeamModel>> getTeams(Context context,String key) {
        return TeamRepository.getInstance(context).getTeams(key);
    }


    public LiveData<List<TeamModel>> getTeamsLocal(Context context,String key){
        return TeamRepository.getInstance(context).getTeamsLocal(key);
    }

}
