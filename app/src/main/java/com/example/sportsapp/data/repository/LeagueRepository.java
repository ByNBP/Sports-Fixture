package com.example.sportsapp.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;

import com.example.sportsapp.data.model.db.AppDao;
import com.example.sportsapp.data.model.db.AppDatabase;
import com.example.sportsapp.data.model.league.LeagueModel;
import com.example.sportsapp.data.model.league.LeagueResponse;
import com.example.sportsapp.network.RetrofitProvider;
import com.example.sportsapp.network.RetrofitService;
import com.example.sportsapp.utils.ConnectionHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeagueRepository {

    RetrofitService leagueApi = RetrofitProvider.getLeagueApi();
    AppDatabase db;
    AppDao dao;

    public LeagueRepository(Context context) {
        db = AppDatabase.getDatabase(context);
        dao = db.leagueDao();
    }

    public static LeagueRepository getInstance(Context context) {
        return new LeagueRepository(context);
    }

    public LiveData<List<LeagueModel>> getLeagues() {

        MutableLiveData<List<LeagueModel>> data = new MutableLiveData<>();

        Call<LeagueResponse> responseCall = leagueApi.getLeagues(ConnectionHelper.API_KEY);

        responseCall.enqueue(new Callback<LeagueResponse>() {
            @Override
            public void onResponse(Call<LeagueResponse> call, Response<LeagueResponse> response) {
                if (response.code() == 200) {
                    LeagueResponse leagueResponse = response.body();

                    if (leagueResponse != null && leagueResponse.getLeagues() != null) {
                        saveLeague(leagueResponse.getLeagues());
                        data.setValue(leagueResponse.getLeagues());
                    } else
                        data.setValue(null);


                } else {
                    data.setValue(null);
                    try {
                        Log.v("TAG", "Error" + response.errorBody().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LeagueResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;

    }

    public LiveData<List<LeagueModel>> getLeagueLocal() {
        LiveData<List<LeagueModel>> data = dao.getLeagues();
        db.close();
        return data;
    }

    public void saveLeague(List<LeagueModel> models) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            dao.clearLeagues();
            dao.insertLeagues(models);
            db.close();
        });
    }
}
