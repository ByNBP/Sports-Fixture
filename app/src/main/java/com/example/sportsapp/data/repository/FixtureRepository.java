package com.example.sportsapp.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sportsapp.data.model.FixtureModel;
import com.example.sportsapp.network.RetrofitProvider;
import com.example.sportsapp.network.RetrofitService;

import java.util.List;


public class FixtureRepository {

    RetrofitService leagueApi = RetrofitProvider.getLeagueApi();

    public FixtureRepository(Context context){

    }

    public static FixtureRepository getInstance(Context context){
        return new FixtureRepository(context);
    }
    public LiveData<List<FixtureModel>> getFixtures(){
        MutableLiveData<FixtureModel> data = new MutableLiveData<>();

        return null;
    }

}
