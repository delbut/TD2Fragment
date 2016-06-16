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
import com.esgi.td2fragment.view.adapter.ViewPagerAdapter;

/**
 * Created by maxime on 16/06/16.
 */
public class ViewPagerFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_pager,null);
        tabLayout = (TabLayout) rootView.findViewById(R.id.sliding_tabs);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), getContext()));

//        tabLayout.post(new Runnable() {
//            @Override
//            public void run() {
                tabLayout.setupWithViewPager(viewPager);
//            }
//        });

        return rootView;

    }

}
