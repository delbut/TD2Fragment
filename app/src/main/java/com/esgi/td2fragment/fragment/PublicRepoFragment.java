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

    private static final String PUBLIC_REPO_NUMBER_KEY = "publicRepNumber";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ui_fragment_number, container, false);
        int numberPubRepo = 0;

        TextView textView = (TextView) rootView.findViewById(R.id.number);

        Bundle bundle = getArguments();
        if (bundle != null) {
            numberPubRepo = bundle.getInt(PUBLIC_REPO_NUMBER_KEY);
        }

        textView.setText(numberPubRepo + " public repositories !");
        return rootView;
    }

    public static PublicRepoFragment newInstance(int publicRep) {
        PublicRepoFragment f = new PublicRepoFragment();
        Bundle args = new Bundle();
        args.putInt(PUBLIC_REPO_NUMBER_KEY, publicRep);
        f.setArguments(args);
        return f;
    }
}
