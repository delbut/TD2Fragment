package com.esgi.td2fragment.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.esgi.td2fragment.R;
import com.esgi.td2fragment.models.Repository;
import com.esgi.td2fragment.view.viewHolder.RepositoriesViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxime on 16/06/16.
 */
public class RepositoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Repository> repositories;

    public RepositoriesAdapter(List<Repository> repositories) {
        this.repositories = repositories;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RepositoriesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof RepositoriesViewHolder) {
            RepositoriesViewHolder repositoriesViewHolder = (RepositoriesViewHolder) holder;
            Repository repository = this.repositories.get(position);
            repositoriesViewHolder.bind(repository);
        }
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }
}
