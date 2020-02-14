package com.example.feedmememes.ActivitiesAndFragments.activityAndFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
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
    final memesAdapter memesAdapter= new memesAdapter(imageList);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView= findViewById(R.id.memesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(memesAdapter);
        final memesViewModel memesViewModel= ViewModelProviders.of(this).get(com.example.feedmememes.ActivitiesAndFragments.viewModels.memesViewModel.class);
        getDefaultMemes(memesViewModel);
        final SearchView searchView=findViewById(R.id.search_view);
        searchView.setQueryHint("Enter Keyword to search");
        searchView.setActivated(true);
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                called when we click search button keypad
//                Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                getSearchMemes(memesViewModel,query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    void getDefaultMemes(memesViewModel memesViewModel){
        memesViewModel.getTrending().observe(this, new Observer<trendingMemesResponse>() {
            @Override
            public void onChanged(trendingMemesResponse trendingMemesResponse) {
                if(trendingMemesResponse.getData().size()>0){
                    Log.d(TAG," size is response is "+trendingMemesResponse.getData().size());
                    List<com.example.feedmememes.ActivitiesAndFragments.models.trendingMemesResponse.memesData> memesDataList=trendingMemesResponse.getData();
                    for (com.example.feedmememes.ActivitiesAndFragments.models.trendingMemesResponse.memesData each: memesDataList){
                        imageDetails temporaryImage= each.getImages().getOriginal();
                        temporaryImage.setTitle(each.getTitle());
//                        Log.d(TAG," title is "+each.getTitle());
                        imageList.add(temporaryImage);
                    }
                    memesAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    void getSearchMemes(memesViewModel memesViewModel,String searchedTerm){
        memesViewModel.getSearchedMemes(searchedTerm).observe(this, new Observer<trendingMemesResponse>() {
            @Override
            public void onChanged(trendingMemesResponse trendingMemesResponse) {
                if(trendingMemesResponse.getData().size()>0){
                    Toast.makeText(MainActivity.this, "got some response and size is "+trendingMemesResponse.getData().size(), Toast.LENGTH_SHORT).show();
                    imageList.clear();
                    List<com.example.feedmememes.ActivitiesAndFragments.models.trendingMemesResponse.memesData> memesDataList=trendingMemesResponse.getData();
                    for (com.example.feedmememes.ActivitiesAndFragments.models.trendingMemesResponse.memesData each: memesDataList){
                        imageDetails temporaryImage= each.getImages().getOriginal();
                        temporaryImage.setTitle(each.getTitle());
                        imageList.add(temporaryImage);
                    }
                    memesAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
