package com.example.sportsapp.network;
import com.example.sportsapp.data.model.league.LeagueResponse;
import com.example.sportsapp.data.model.team.TeamResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("sport/leaguesList")
    Call<LeagueResponse> getLeagues( @Header("authorization") String key);

    @GET("sport/league")
    Call<TeamResponse> getTeams(@Header("authorization") String key,@Query("data.league") String dataLeague);

}
