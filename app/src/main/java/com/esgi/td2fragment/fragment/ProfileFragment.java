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

    private View rootView;
    private CircleImageView civProfile;
    private TextView tvUsername;

    public static Fragment newInstance(){
        return new ProfileFragment(); //Correction Exemple de newInstance sans arguments
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        initView();

        return this.rootView;
    }

    private void initView() {
        civProfile = (CircleImageView) this.rootView.findViewById(R.id.civ_profile);
        tvUsername = (TextView) this.rootView.findViewById(R.id.tv_username);

        User user = SessionData.getInstance().getCurrentUser();

        Picasso.with(getContext()).load(user.getAvatar_url()).into(civProfile);
        tvUsername.setText(user.getLogin());
    }
}
