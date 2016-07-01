package com.esgi.td2fragment.view.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.esgi.td2fragment.R;
import com.esgi.td2fragment.models.Repository;

/**
 * Created by maxime on 16/06/16.
 */
public class RepositoriesViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvTitle;
    private final TextView tvDescribe;

    public RepositoriesViewHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvDescribe = (TextView) itemView.findViewById(R.id.tv_describe);
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public TextView getTvDescribe() {
        return tvDescribe;
    }

    public void bind(Repository repository) {
        getTvDescribe().setText(repository.getDescription());
        getTvTitle().setText(repository.getName());
    }
}
