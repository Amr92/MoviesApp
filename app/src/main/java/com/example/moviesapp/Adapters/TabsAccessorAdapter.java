package com.example.moviesapp.Adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.moviesapp.Fragments.CelebsFragment;
import com.example.moviesapp.Fragments.MoviesFragment;
import com.example.moviesapp.Fragments.SearchFragment;
import com.example.moviesapp.Fragments.TVSeriesFragment;

public class TabsAccessorAdapter extends FragmentPagerAdapter {

    public TabsAccessorAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                MoviesFragment moviesFragment = new MoviesFragment();
                return moviesFragment;

            case 1:
                TVSeriesFragment tvSeriesFragment = new TVSeriesFragment();
                return tvSeriesFragment;

            case 2:
                CelebsFragment celebsFragment = new CelebsFragment();
                return celebsFragment;

            case 3:
                SearchFragment searchFragment = new SearchFragment();
                return searchFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Movies";

            case 1:
                return "TV";

            case 2:
                return "Celebs";

            case 3:
                return "Search";

            default:
                return null;
        }
    }
}
