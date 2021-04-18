package com.example.sportsapp.ui.fragments.league;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sportsapp.data.model.league.LeagueModel;
import com.example.sportsapp.data.repository.LeagueRepository;

import java.util.ArrayList;
import java.util.List;

public class LeagueViewModel extends ViewModel {


    public LiveData<List<LeagueModel>> getLeagues(Context context){
        return LeagueRepository.getInstance(context).getLeagues();
    }

    public LiveData<List<LeagueModel>> getLeaguesLocal(Context context){
        return LeagueRepository.getInstance(context).getLeagueLocal();
    }

}
