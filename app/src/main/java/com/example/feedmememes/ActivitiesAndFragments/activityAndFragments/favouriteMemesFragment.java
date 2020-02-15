package com.example.feedmememes.ActivitiesAndFragments.activityAndFragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.feedmememes.ActivitiesAndFragments.dbUtils.dbViewModel;
import com.example.feedmememes.ActivitiesAndFragments.models.memesDBObject;
import com.example.feedmememes.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class favouriteMemesFragment extends Fragment {
    private dbViewModel mdbViewModel;
    private TextView textView;
    private String info="";
    public favouriteMemesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favourite_memes, container, false);
        textView=view.findViewById(R.id.memeText);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mdbViewModel = new ViewModelProvider(this).get(dbViewModel.class);
        mdbViewModel.getAllWords().observe(this, new Observer<List<memesDBObject>>() {
            @Override
            public void onChanged(List<memesDBObject> memesDBObjects) {
                for(int i=0;i<memesDBObjects.size();i++){
                    info= info+ memesDBObjects.get(i).getTitle();
                }
                textView.setText(info);
            }
        });
    }
}
