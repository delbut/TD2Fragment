package com.esgi.td2fragment.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.esgi.td2fragment.R;
import com.esgi.td2fragment.fragment.FollowedFragment;
import com.esgi.td2fragment.fragment.FollowersFragment;
import com.esgi.td2fragment.fragment.PublicRepoFragment;
import com.esgi.td2fragment.models.User;

/**
 * Created by maxime on 16/06/16.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public static final int PAGE_PUBLIC_REPO = 0;
    public static final int PAGE_FOLLOWER = 1;
    public static final int PAGE_FOLLOWED = 2;

    private final String[] tabTitles;
    private final int pageCount;
    private Fragment[] fragmentPages;
    private final User user;

    public ViewPagerAdapter(FragmentManager fm, Context context, User user) {
        super(fm);
        tabTitles = context.getResources().getStringArray(R.array.app_tabs);
        pageCount = tabTitles.length;
        fragmentPages = new Fragment[pageCount];
        this.user = user;
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case PAGE_PUBLIC_REPO:
                if (fragmentPages[position] == null) {
                    fragmentPages[position] = PublicRepoFragment.newInstance(this.user.getPublic_repos());
                }
                fragment = fragmentPages[position];
                break;
            case PAGE_FOLLOWER:
                if (fragmentPages[position] == null) {
                    fragmentPages[position] = FollowersFragment.newInstance(this.user.getFollowers());
                }
                fragment = fragmentPages[position];
                break;
            case PAGE_FOLLOWED:
                if (fragmentPages[position] == null) {
                    fragmentPages[position] = FollowedFragment.newInstance(this.user.getFollowing());
                }
                fragment = fragmentPages[position];
                break;
            default:
                if (fragmentPages[PAGE_PUBLIC_REPO] == null) {
                    fragmentPages[PAGE_PUBLIC_REPO] = PublicRepoFragment.newInstance(this.user.getPublic_repos());
                }
                fragment = fragmentPages[PAGE_PUBLIC_REPO];
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return pageCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
