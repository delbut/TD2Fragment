package com.esgi.td2fragment.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esgi.td2fragment.R;
import com.esgi.td2fragment.models.User;
import com.esgi.td2fragment.utils.SessionData;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by maxime on 16/06/16.
 */
public class ProfileFragment extends Fragment {

    private static final String PICTURE_URL_KEY = "pictureUrl";
    private static final String LOGIN_KEY = "login";

    private View rootView;
    private String pictureUrl;
    private String login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            pictureUrl = bundle.getString(PICTURE_URL_KEY);
            login = bundle.getString(LOGIN_KEY);
        }
        initView();

        return this.rootView;
    }

    private void initView() {
        CircleImageView civProfile = (CircleImageView) this.rootView.findViewById(R.id.civ_profile);
        TextView tvUsername = (TextView) this.rootView.findViewById(R.id.tv_username);
        Picasso.with(getContext()).load(pictureUrl).into(civProfile);
        tvUsername.setText(login);
    }

    public static ProfileFragment newInstance(String urlPicture, String login) {
        ProfileFragment f = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(PICTURE_URL_KEY, urlPicture);
        args.putString(LOGIN_KEY, login);
        f.setArguments(args);
        return f;
    }
}
