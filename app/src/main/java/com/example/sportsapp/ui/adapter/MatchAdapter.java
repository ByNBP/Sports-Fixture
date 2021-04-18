package com.example.sportsapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sportsapp.R;
import com.example.sportsapp.data.model.MatchModel;

import java.util.ArrayList;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchHolder> {

    ArrayList<MatchModel> matches = new ArrayList<>();

    public void setMatches(ArrayList<MatchModel> matches) {
        this.matches = matches;
    }

    @NonNull
    @Override
    public MatchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.itemview_match, parent, false);
        return new MatchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchHolder holder, int position) {
        MatchModel model = matches.get(position);

        holder.home.setText(model.getHome());
        holder.away.setText(model.getAway());
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class MatchHolder extends RecyclerView.ViewHolder {
        TextView home;
        TextView away;

        public MatchHolder(@NonNull View itemView) {
            super(itemView);
            home = itemView.findViewById(R.id.home_team);
            away = itemView.findViewById(R.id.away_team);

        }
    }

}
