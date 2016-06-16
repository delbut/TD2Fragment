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
public class PublicRepoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ui_fragment_number, container, false);

        TextView textView = (TextView) rootView.findViewById(R.id.number);

        textView.setText(SessionData.getInstance().getCurrentUser().getPublic_repos() + " public repositories !");
        return rootView;
    }
}
