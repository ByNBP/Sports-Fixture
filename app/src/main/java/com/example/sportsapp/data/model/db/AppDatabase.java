package com.example.sportsapp.data.model.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.sportsapp.data.converter.Converter;
import com.example.sportsapp.data.model.league.LeagueModel;
import com.example.sportsapp.data.model.team.TeamModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {LeagueModel.class, TeamModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final int THREAD = 2;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(THREAD);

    public static AppDatabase getDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "leagues_db")
                .setJournalMode(JournalMode.AUTOMATIC)
                .fallbackToDestructiveMigration()
                .build();
    }

    public abstract AppDao leagueDao();
}
