package com.esgi.td2fragment.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxime on 16/06/16.
 */
public class RepositoriesFragment extends Fragment {
    private static final String LOGIN_KEY = "login";

    private View rootView;
    private RecyclerView recyclerView;
    private RepositoriesAdapter adapter;
    private String login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_repositories, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            login = bundle.getString(LOGIN_KEY);
        }

        recyclerView = (RecyclerView) this.rootView.findViewById(R.id.rl_repo);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(llm);

        loadListRepo();
        return this.rootView;
    }

    private void loadListRepo() {
        GitHubAPIService gitHubAPIService = GitHubAPIService.getInstance();

        gitHubAPIService.getReposUser(login, new GitHubAPIService.ApiResult<List<Repository>>() {
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

    public static RepositoriesFragment newInstance(String login) {
        RepositoriesFragment f = new RepositoriesFragment();
        Bundle args = new Bundle();
        args.putString(LOGIN_KEY, login);
        f.setArguments(args);
        return f;
    }
}
