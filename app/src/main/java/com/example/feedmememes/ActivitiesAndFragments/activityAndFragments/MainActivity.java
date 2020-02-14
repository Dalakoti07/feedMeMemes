package com.example.feedmememes.ActivitiesAndFragments.activityAndFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feedmememes.ActivitiesAndFragments.adapter.memesAdapter;
import com.example.feedmememes.ActivitiesAndFragments.models.imageDetails;
import com.example.feedmememes.ActivitiesAndFragments.models.trendingMemesResponse;
import com.example.feedmememes.ActivitiesAndFragments.viewModels.memesViewModel;
import com.example.feedmememes.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String urls="";
    private String TAG="commonTag";
    ArrayList<imageDetails> imageList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView= findViewById(R.id.memesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final memesAdapter memesAdapter= new memesAdapter(imageList);
        recyclerView.setAdapter(memesAdapter);

//        using view-model
        memesViewModel memesViewModel= ViewModelProviders.of(this).get(com.example.feedmememes.ActivitiesAndFragments.viewModels.memesViewModel.class);
        memesViewModel.getTrending().observe(this, new Observer<trendingMemesResponse>() {
            @Override
            public void onChanged(trendingMemesResponse trendingMemesResponse) {
                if(trendingMemesResponse.getData().size()>0){
                    Log.d(TAG," size is response is "+trendingMemesResponse.getData().size());
                    List<com.example.feedmememes.ActivitiesAndFragments.models.trendingMemesResponse.memesData> memesDataList=trendingMemesResponse.getData();
                    for (com.example.feedmememes.ActivitiesAndFragments.models.trendingMemesResponse.memesData each: memesDataList){
                        imageList.add(each.getImages().getOriginal());
                    }
                    memesAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
