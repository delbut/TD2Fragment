package com.esgi.td2fragment.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.esgi.td2fragment.R;
import com.esgi.td2fragment.models.Repository;
import com.esgi.td2fragment.network.GitHubAPIService;
import com.esgi.td2fragment.utils.SessionData;
import com.esgi.td2fragment.view.adapter.RepositoriesAdapter;

import java.util.List;

/**
 * Created by maxime on 16/06/16.
 */
public class RepositoriesFragment extends Fragment {

    private RecyclerView recyclerView;
    private RepositoriesAdapter adapter;

    public static Fragment newInstance() {
        return new RepositoriesFragment();  //Correction Exemple de newInstance sans arguments
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repositories, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.rl_repo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        loadListRepo();
    }

    private void loadListRepo() {
        GitHubAPIService gitHubAPIService = GitHubAPIService.getInstance();

        gitHubAPIService.getReposUser(SessionData.getInstance().getCurrentUser().getLogin(), new GitHubAPIService.ApiResult<List<Repository>>() {
            @Override
            public void success(List<Repository> res) {
                adapter = new RepositoriesAdapter(res);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void error(int code, String message) {
                Toast.makeText(getContext(), "code : " + code + ", " + message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
