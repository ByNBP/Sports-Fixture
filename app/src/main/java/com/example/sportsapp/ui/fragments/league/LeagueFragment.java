package com.example.sportsapp.ui.fragments.league;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sportsapp.R;
import com.example.sportsapp.data.model.league.LeagueModel;

import com.example.sportsapp.ui.fragments.team.TeamsFragment;
import com.example.sportsapp.utils.NavigationManager;

import java.util.ArrayList;
import java.util.List;


public class LeagueFragment extends Fragment {

    private LeagueViewModel viewModel;

    private ListView liste;

    public List<LeagueModel> leagueModals;
    public static ArrayList<String> leagueName = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(LeagueViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_league, container, false);

        liste = view.findViewById(R.id.liste);

        getDataLocal();

        liste.setOnItemClickListener((p, v, pos, id) -> {
            String key = leagueModals.get(pos).getKey();

            Fragment fragment = TeamsFragment.newInstance(key);

            NavigationManager.navigate(fragment, "page");
        });

        return view;
    }

    private void getData() {
        viewModel.getLeagues(requireContext()).observe(getViewLifecycleOwner(), this::setData);
    }

    private void getDataLocal() {
        viewModel.getLeaguesLocal(requireContext()).observe(getViewLifecycleOwner(), leagueModals -> {
            if (leagueModals.size() > 0)
                setData(leagueModals);
            else
                getData();
        });
    }

    private void setData(List<LeagueModel> models) {
        if (models != null) {
            this.leagueModals = models;

            ArrayList<String> leagueName = new ArrayList<>();

            for (LeagueModel modal : models) {
                leagueName.add(modal.getLeague());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.itemview_league, leagueName);
            liste.setAdapter(adapter);

        } else {
            Toast.makeText(requireContext(), "İstek başarısız", Toast.LENGTH_SHORT).show();
        }
    }

}