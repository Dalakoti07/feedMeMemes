package com.example.feedmememes.ActivitiesAndFragments.activityAndFragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feedmememes.ActivitiesAndFragments.adapter.favMemesAdapter;
import com.example.feedmememes.ActivitiesAndFragments.adapter.memesAdapter;
import com.example.feedmememes.ActivitiesAndFragments.dbUtils.dbViewModel;
import com.example.feedmememes.ActivitiesAndFragments.models.imageDetails;
import com.example.feedmememes.ActivitiesAndFragments.models.memesDBObject;
import com.example.feedmememes.ActivitiesAndFragments.network.downloadResultReceiver;
import com.example.feedmememes.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class favouriteMemesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<imageDetails> imageList= new ArrayList<>();
    private final com.example.feedmememes.ActivitiesAndFragments.adapter.favMemesAdapter memesAdapter= new favMemesAdapter(imageList);



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favourite_memes, container, false);
        recyclerView=view.findViewById(R.id.memesRecyclerView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(memesAdapter);
        dbViewModel mdbViewModel = new ViewModelProvider(this).get(dbViewModel.class);
        mdbViewModel.getAllWords().observe(this, new Observer<List<memesDBObject>>() {
            @Override
            public void onChanged(List<memesDBObject> memesDBObjects) {
                convertDBListToAdapterList(memesDBObjects);
//                Toast.makeText(getActivity(), "total fav memes are " +memesDBObjects.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void convertDBListToAdapterList(List<memesDBObject> memesDBObjects) {
        imageList.clear();
        for(memesDBObject object: memesDBObjects){
                Log.d("commonTag"," db stuff title :"+object.getTitle()+" url is :"+object.fullPath);
                imageList.add(new imageDetails(object.getImageId(),object.getFullPath(),object.getTitle(),true,object.getUriPath()));
            }
        memesAdapter.notifyDataSetChanged();
    }


}
