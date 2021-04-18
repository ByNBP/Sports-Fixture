package com.example.sportsapp;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sportsapp.data.model.team.TeamModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    public MutableLiveData<List<TeamModel>> teams = new MutableLiveData<>();

    public void setTeams(List<TeamModel> teams) {
        this.teams.setValue(teams);
    }

}
