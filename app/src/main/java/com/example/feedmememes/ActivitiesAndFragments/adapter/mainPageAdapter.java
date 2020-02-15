package com.example.feedmememes.ActivitiesAndFragments.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.feedmememes.ActivitiesAndFragments.activityAndFragments.favouriteMemesFragment;
import com.example.feedmememes.ActivitiesAndFragments.activityAndFragments.searchMemesFragment;

public class mainPageAdapter extends FragmentStatePagerAdapter {

    public  mainPageAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 1: return new favouriteMemesFragment();
            default: return new searchMemesFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 1: return "Favourites";
            default: return "Search";
        }
    }
}
