package com.esgi.td2fragment.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esgi.td2fragment.R;
import com.esgi.td2fragment.utils.SessionData;

/**
 * Created by maxime on 16/06/16.
 */
public class FollowersFragment extends Fragment {
    private static final String FOLLOWERS_NUMBER_KEY = "followersNumber";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ui_fragment_number, container, false);
        int followers = 0;

        Bundle bundle = getArguments();
        if (bundle != null) {
            followers = bundle.getInt(FOLLOWERS_NUMBER_KEY);
        }

        TextView textView = (TextView) rootView.findViewById(R.id.number);

        textView.setText(followers + " followers !");
        return rootView;
    }

    public static FollowersFragment newInstance(int followers) {
        FollowersFragment f = new FollowersFragment();
        Bundle args = new Bundle();
        args.putInt(FOLLOWERS_NUMBER_KEY, followers);
        f.setArguments(args);
        return f;
    }
}
