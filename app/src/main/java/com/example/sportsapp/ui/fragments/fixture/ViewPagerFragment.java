package com.example.sportsapp.ui.fragments.fixture;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sportsapp.MainViewModel;
import com.example.sportsapp.R;
import com.example.sportsapp.data.model.team.TeamModel;
import com.example.sportsapp.data.model.FixtureModel;
import com.example.sportsapp.data.model.MatchModel;
import com.example.sportsapp.ui.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerFragment extends Fragment {

    private MainViewModel mainViewModel;

    ViewPager2 viewPager2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        viewPager2 = view.findViewById(R.id.viewPager);

        mainViewModel.teams.observe((LifecycleOwner) this, teamModels -> {
            ArrayList<FixtureModel> fixtures = getFixtures(teamModels);

            ViewPagerAdapter adapter = new ViewPagerAdapter();
            adapter.setFixtures(fixtures);

            viewPager2.setAdapter(adapter);
        });

        return view;
    }

    private ArrayList<FixtureModel> getFixtures(List<TeamModel> teamModels) {
        ArrayList<FixtureModel> fixtures = new ArrayList<>();
        int numberOfTeams = teamModels.size();
        // If odd number of teams add a "ghost".
        if (numberOfTeams % 2 != 0) {
            numberOfTeams++;
        }
        // Generate the fixtures using the cyclic algorithm.
        int totalWeek = numberOfTeams - 1;
        int matchesPerWeek = numberOfTeams / 2;
        for (int week = 0; week < totalWeek; week++) {
            String ghost = null;
            ArrayList<MatchModel> matches = new ArrayList<>();
            for (int match = 0; match < matchesPerWeek; match++) {
                int home = (week + match) % (numberOfTeams - 1);
                int away = (numberOfTeams - 1 - match + week) % (numberOfTeams - 1);
                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0)
                    away = numberOfTeams - 1;
                if (home == teamModels.size() | away == teamModels.size()) {
                    if (home != teamModels.size())
                        ghost = teamModels.get(home).getTeam();
                    else
                        ghost = teamModels.get(away).getTeam();
                    continue;
                }
                matches.add(new MatchModel(teamModels.get(home).getTeam(), teamModels.get(away).getTeam()));
            }
            if (ghost != null)
                fixtures.add(new FixtureModel(ghost, matches));
            else
                fixtures.add(new FixtureModel(matches));
        }
        // Interleave so that home and away games are fairly evenly dispersed.
        ArrayList<FixtureModel> interleaved = new ArrayList<>();

        int evn = 0;
        int odd = (numberOfTeams / 2);
        for (int i = 0; i < fixtures.size(); i++) {
            if (i % 2 == 0)
                interleaved.add(fixtures.get(evn++));
            else
                interleaved.add(fixtures.get(odd++));
        }
        fixtures = interleaved;
        // Last team can't be away for every game so flip them
        // to home on odd rounds.
        for (int roundNumber = 0; roundNumber < fixtures.size(); roundNumber++) {
            if (roundNumber % 2 == 1) {
                MatchModel matchModel = fixtures.get(roundNumber).getMatch(0);
                fixtures.get(roundNumber).setMatch(0, new MatchModel(matchModel.getAway(), matchModel.getHome()));
            }
        }
        ArrayList<FixtureModel> reverseFixtures = new ArrayList<>();

        for (FixtureModel fixture : fixtures) {
            ArrayList<MatchModel> reverseMatches = new ArrayList<>();

            for (MatchModel model : fixture.getMatches()) {
                reverseMatches.add(new MatchModel(model.getAway(), model.getHome()));
            }

            reverseFixtures.add(new FixtureModel(fixture.getGhostTeam(), reverseMatches));
        }

        fixtures.addAll(reverseFixtures);

        return fixtures;
    }

}
