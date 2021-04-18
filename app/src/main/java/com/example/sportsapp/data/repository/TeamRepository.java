package com.example.sportsapp.data.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sportsapp.data.model.db.AppDao;
import com.example.sportsapp.data.model.db.AppDatabase;
import com.example.sportsapp.data.model.league.LeagueModel;
import com.example.sportsapp.data.model.team.TeamModel;
import com.example.sportsapp.data.model.team.TeamResponse;
import com.example.sportsapp.network.RetrofitProvider;
import com.example.sportsapp.network.RetrofitService;
import com.example.sportsapp.utils.ConnectionHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamRepository {

    RetrofitService leagueApi = RetrofitProvider.getLeagueApi();
    AppDatabase db;
    AppDao dao;

    public TeamRepository(Context context) {
        db = AppDatabase.getDatabase(context);
        dao = db.leagueDao();
    }

    public static TeamRepository getInstance(Context context) {
        return new TeamRepository(context);
    }

    public LiveData<List<TeamModel>> getTeams(String key) {

        MutableLiveData<List<TeamModel>> data = new MutableLiveData<>();

        Call<TeamResponse> responseCall = leagueApi.getTeams(ConnectionHelper.API_KEY, key);
        responseCall.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.code() == 200) {

                    TeamResponse teamResponse = response.body();

                    if (teamResponse != null && teamResponse.getTeams() != null) {
                        ArrayList<TeamModel> teamModels = teamResponse.getTeams();

                        for (TeamModel model : teamModels) {
                            model.setKey(key);
                        }
                        saveTeams(teamModels);
                        data.setValue(teamModels);
                    } else
                        data.setValue(null);


                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<List<TeamModel>> getTeamsLocal(String key){
        LiveData<List<TeamModel>> data = dao.getTeams(key);
        db.close();
        return data;

    }

    public void saveTeams(List<TeamModel> models) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            dao.insertTeams(models);
            db.close();
        });
    }

}
