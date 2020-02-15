package com.example.feedmememes.ActivitiesAndFragments.activityAndFragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.feedmememes.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class favouriteMemesFragment extends Fragment {


    public favouriteMemesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite_memes, container, false);
    }

}
