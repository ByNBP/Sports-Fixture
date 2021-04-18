package com.example.sportsapp.ui.fragments.team;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.sportsapp.MainViewModel;
import com.example.sportsapp.R;
import com.example.sportsapp.data.model.league.LeagueModel;
import com.example.sportsapp.data.model.team.TeamModel;
import com.example.sportsapp.network.RetrofitProvider;
import com.example.sportsapp.data.model.team.TeamResponse;
import com.example.sportsapp.ui.fragments.fixture.ViewPagerFragment;
import com.example.sportsapp.utils.ConnectionHelper;
import com.example.sportsapp.network.RetrofitService;
import com.example.sportsapp.utils.NavigationManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TeamsFragment extends Fragment {

    private TeamViewModel viewModel;
    private MainViewModel mainViewModel;

    String key;

    ListView teamsList;
    ImageView fikstureButton;
    public List<TeamModel> teamModels;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        key = getArguments().getString("key");

    }

    public static TeamsFragment newInstance(String teamKey) {
        Bundle args = new Bundle();
        args.putString("key", teamKey);

        TeamsFragment fragment = new TeamsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teams, container, false);

        fikstureButton = view.findViewById(R.id.fikstureButton);
        teamsList = view.findViewById(R.id.teamsList);

        fikstureButton.setOnClickListener(v -> {
            Fragment fragment = new ViewPagerFragment();
            mainViewModel.setTeams(teamModels);
            NavigationManager.navigate( fragment, "page");
        });

        getDataLocal();

        return view;
    }

    private void getDataLocal() {
        viewModel.getTeamsLocal(requireContext(),key).observe(getViewLifecycleOwner(), teamModels -> {
            if (teamModels.size() > 0)
                setData(teamModels);
            else
                getData();
        });
    }

    private void getData() {
        viewModel.getTeams(requireContext(),key).observe(getViewLifecycleOwner(), this::setData);
    }

    private void setData(List<TeamModel> models) {
        if (models != null) {
            this.teamModels = models;

            ArrayList<String> teamNames = new ArrayList<>();

            for (TeamModel model : models) {
                teamNames.add(model.getTeam());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.itemview_league, teamNames);
            teamsList.setAdapter(adapter);

        } else {
            Toast.makeText(requireContext(), "İstek başarısız", Toast.LENGTH_SHORT).show();
        }
    }

}