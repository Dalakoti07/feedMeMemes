package com.example.feedmememes.ActivitiesAndFragments.activityAndFragments;


import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.feedmememes.ActivitiesAndFragments.adapter.memesAdapter;
import com.example.feedmememes.ActivitiesAndFragments.adapter.swipeController;
import com.example.feedmememes.ActivitiesAndFragments.adapter.swipeControllerActions;
import com.example.feedmememes.ActivitiesAndFragments.dbUtils.dbViewModel;
import com.example.feedmememes.ActivitiesAndFragments.models.constantsClass;
import com.example.feedmememes.ActivitiesAndFragments.models.imageDetails;
import com.example.feedmememes.ActivitiesAndFragments.models.memesDBObject;
import com.example.feedmememes.ActivitiesAndFragments.models.trendingMemesResponse;
import com.example.feedmememes.ActivitiesAndFragments.network.downloadResultReceiver;
import com.example.feedmememes.ActivitiesAndFragments.network.downloadService;
import com.example.feedmememes.ActivitiesAndFragments.viewModels.memesViewModel;
import com.example.feedmememes.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

/**
 * A simple {@link Fragment} subclass.
 */
public class searchMemesFragment extends Fragment implements downloadResultReceiver.callDB{
//    private DownloadManager manager = null;
    private String TAG="commonTag";
    ArrayList<imageDetails> imageList= new ArrayList<>();
    private final com.example.feedmememes.ActivitiesAndFragments.adapter.memesAdapter memesAdapter= new memesAdapter(imageList);
    private RecyclerView recyclerView;
    private SearchView searchView;
    private dbViewModel mdbViewModel;

    public searchMemesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search_memes, container, false);
        recyclerView=view.findViewById(R.id.memesRecyclerView);
        searchView=view.findViewById(R.id.search_view);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.floating_action_button);
//        floatingActionButton.getDrawable().mutate().setTint(Color.parseColor(constantsClass.currentDarkPrimaryColor));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mdbViewModel = new ViewModelProvider(this).get(dbViewModel.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(memesAdapter);
        final swipeController swipeController = new swipeController(new swipeControllerActions(){
            @Override
            public void onLeftClicked(final int position) {
                final memesDBObject object=memesAdapter.getObjectAtPosition(position);

                mdbViewModel.getById(object.getImageId()).observe(searchMemesFragment.this, new Observer<List<memesDBObject>>() {
                    @Override
                    public void onChanged(List<memesDBObject> memesDBObjects) {
                        if(memesDBObjects.size()==0){
                            Toast.makeText(getActivity(), "Adding item at position to fav list"+position, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), downloadService.class);
                            intent.putExtra("url", object.getFullPath());
                            intent.putExtra("position",position);
                            downloadResultReceiver downloadResultReceiver =new downloadResultReceiver(new Handler());
                            downloadResultReceiver.addListener(searchMemesFragment.this);
                            intent.putExtra("receiver", downloadResultReceiver);
                            intent.putExtra("fileName",object.getImageId()+".gif");
                            Objects.requireNonNull(getActivity()).startService(intent);
                        }else{
                            Toast.makeText(getActivity(), "Already exist in favourite list "+position, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
        final memesViewModel memesViewModel= ViewModelProviders.of(this).get(com.example.feedmememes.ActivitiesAndFragments.viewModels.memesViewModel.class);
        getDefaultMemes(memesViewModel);
        searchView.setQueryHint("Enter Keyword to search");
        searchView.setActivated(true);
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                called when we click search button keypad
                getSearchMemes(memesViewModel,query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void getDefaultMemes(memesViewModel memesViewModel){
        memesViewModel.getTrending().observe(this, new Observer<trendingMemesResponse>() {
            @Override
            public void onChanged(trendingMemesResponse trendingMemesResponse) {
                if(trendingMemesResponse.getData().size()>0){
                    Log.d(constantsClass.logTag," size is response is "+trendingMemesResponse.getData().size());
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

    private void getSearchMemes(memesViewModel memesViewModel,String searchedTerm){
        memesViewModel.getSearchedMemes(searchedTerm).observe(this, new Observer<trendingMemesResponse>() {
            @Override
            public void onChanged(trendingMemesResponse trendingMemesResponse) {
                if(trendingMemesResponse.getData().size()>0){
                    Toast.makeText(getActivity(), "got some response and size is "+trendingMemesResponse.getData().size(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onDetach() {
        super.onDetach();
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
            recyclerView = null;
        }
    }

    @Override
    public void doSomeTaskInDB(final int position) {
        final memesDBObject object=memesAdapter.getObjectAtPosition(position);
        object.setDownloaded(true);
        object.setFullPath(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS)+"/"+object.getImageId()+".gif");// id here is hash
        Log.d(constantsClass.logTag," after download we have path as "+Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS)+"/"+object.getFullPath());
        object.setUriPath(Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS)+"/"+object.getFullPath())).toString());
        Log.d(constantsClass.logTag," after download we have uri as "+object.getUriPath());
//        cross checking, to avoid loop hole in insertion and avoiding crash
        mdbViewModel.getById(object.getImageId()).observe(searchMemesFragment.this, new Observer<List<memesDBObject>>() {
            @Override
            public void onChanged(List<memesDBObject> memesDBObjects) {
                if(memesDBObjects.size()==0){
                    Toast.makeText(getActivity(), "element at position Added to database "+position, Toast.LENGTH_SHORT).show();
                    Log.d(constantsClass.logTag," insertingintodb ");
                    mdbViewModel.insert(object);
                }else{
                    Toast.makeText(getActivity(), "Already exist in database "+position, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
