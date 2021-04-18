package com.example.sportsapp;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.sportsapp.ui.fragments.league.LeagueFragment;
import com.example.sportsapp.utils.NavigationManager;

public class MainActivity extends AppCompatActivity {
    ListView liste;
    ArrayAdapter adapter;
    FrameLayout frameLayout;
    Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.fragmentLayout);
        fragment = new LeagueFragment();
        NavigationManager.setManager(getSupportFragmentManager());

        NavigationManager.navigate(fragment, "page");

    }


}