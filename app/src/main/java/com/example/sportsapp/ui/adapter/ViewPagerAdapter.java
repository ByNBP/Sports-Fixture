package com.example.sportsapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsapp.R;
import com.example.sportsapp.data.model.FixtureModel;


import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.FixtureHolder> {
    ArrayList<FixtureModel> fixtures;

    public void setFixtures(ArrayList<FixtureModel> fixtures) {
        this.fixtures = fixtures;
    }

    @NonNull
    @Override
    public FixtureHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.itemview_fixture, parent, false);
        return new FixtureHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FixtureHolder holder, int position) {

        FixtureModel fixture = fixtures.get(position);

        holder.title.setText((position + 1) + " HAFTA");

        MatchAdapter adapter = new MatchAdapter();
        adapter.setMatches(fixture.getMatches());

        holder.liste.setAdapter(adapter);

        if (fixture.hasGhostTeam())
            holder.ghost.setText("Bay Geçen Takım : " + fixture.getGhostTeam());
    }

    @Override
    public int getItemCount() {
        return fixtures.size();
    }

    class FixtureHolder extends RecyclerView.ViewHolder {
        TextView title;
        RecyclerView liste;
        TextView ghost;

        public FixtureHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            liste = itemView.findViewById(R.id.fixture_list);
            ghost = itemView.findViewById(R.id.ghost_team);

        }
    }

}
