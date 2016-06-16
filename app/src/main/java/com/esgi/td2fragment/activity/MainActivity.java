package com.esgi.td2fragment.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.esgi.td2fragment.R;
import com.esgi.td2fragment.fragment.ProfileFragment;
import com.esgi.td2fragment.fragment.PublicRepoFragment;
import com.esgi.td2fragment.fragment.RepositoriesFragment;
import com.esgi.td2fragment.fragment.ViewPagerFragment;
import com.esgi.td2fragment.models.User;
import com.esgi.td2fragment.utils.SessionData;
import com.esgi.td2fragment.view.adapter.ViewPagerAdapter;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener{

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);

        this.mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.drawerToggle = this.setupDrawerToggle();
        mDrawer.addDrawerListener(this.drawerToggle);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
        setFirstItemNavigationView();

        View headerView;
        if (nvDrawer != null) {
            headerView = nvDrawer.getHeaderView(0);
            CircleImageView circleImageView = (CircleImageView) headerView.findViewById(R.id.iv_profile);
            TextView tvName = (TextView) headerView.findViewById(R.id.nav_tv_name);
            User currentUser = SessionData.getInstance().getCurrentUser();
            tvName.setText(currentUser.getLogin());
            Picasso.with(getBaseContext()).load(currentUser.getAvatar_url()).into(circleImageView);
        }
    }

    private void setFirstItemNavigationView() {
        nvDrawer.setCheckedItem(R.id.nav_profile);
        nvDrawer.getMenu().performIdentifierAction(R.id.nav_profile, 0);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem, navigationView);
                        return true;
                    }
                });
    }
    protected void showHideActionBar(boolean isShowNeeded) {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (isShowNeeded) {
                actionBar.show();
            } else {
                actionBar.hide();
            }
        }
    }
    public void selectDrawerItem(MenuItem menuItem, NavigationView navigationView) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nav_profile:
                fragmentClass = ProfileFragment.class;
                break;
            case R.id.nav_repositories:
                fragmentClass = RepositoriesFragment.class;
                break;
            case R.id.nav_viewpager:
                fragmentClass = ViewPagerFragment.class;
                break;
            default:
                fragmentClass = ProfileFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.flContent, fragment)
                .commit();

        navigationView.setCheckedItem(menuItem.getItemId());
        setTitle(menuItem.getTitle());
        mDrawer.closeDrawers();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
