package com.example.quizapp.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;

import java.util.List;

public class HistoriqueAdapter extends RecyclerView.Adapter<HistoriqueAdapter.ViewHolder> {

    private List<Historique> historiqueList;

    public HistoriqueAdapter(List<Historique> historiqueList) {
        this.historiqueList = historiqueList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView, actionTextView;

        public ViewHolder(View view) {
            super(view);
            dateTextView = view.findViewById(R.id.textDate);
            actionTextView = view.findViewById(R.id.textAction);
        }
    }

    @Override
    public HistoriqueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.historique_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Historique item = historiqueList.get(position);
        holder.dateTextView.setText(item.getDate());
        holder.actionTextView.setText(item.getQuizName()+ "----> "+item.getScore());
    }

    @Override
    public int getItemCount() {
        return historiqueList.size();
    }
}

