package com.example.sportsapp.network;


import com.example.sportsapp.utils.ConnectionHelper;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {


    private static Retrofit retrofit = getRetrofitBuilder().build();

    private static RetrofitService leagueApi = retrofit.create(RetrofitService.class);

    private static Retrofit.Builder getRetrofitBuilder() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ConnectionHelper.BASE_PATH)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create());

        return builder;
    }

    public static RetrofitService getLeagueApi() {
        return leagueApi;
    }

}
