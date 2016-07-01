package com.esgi.td2fragment.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esgi.td2fragment.R;
import com.esgi.td2fragment.activity.MainActivity;
import com.esgi.td2fragment.models.User;
import com.esgi.td2fragment.view.adapter.ViewPagerAdapter;

/**
 * Created by maxime on 16/06/16.
 */
public class ViewPagerFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    private User user;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_pager, null);
        tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

        Bundle bundle = getArguments();
        if (bundle != null) {
            user = bundle.getParcelable(MainActivity.USER_DATA_KEY);
        }

        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), getContext(), user));

//        tabLayout.post(new Runnable() {
//            @Override
//            public void run() {
                tabLayout.setupWithViewPager(viewPager);
//            }
//        });

        return rootView;

    }

    public static ViewPagerFragment newInstance(User user) {
        ViewPagerFragment f = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putParcelable(MainActivity.USER_DATA_KEY, user);
        f.setArguments(args);
        return f;
    }
}
