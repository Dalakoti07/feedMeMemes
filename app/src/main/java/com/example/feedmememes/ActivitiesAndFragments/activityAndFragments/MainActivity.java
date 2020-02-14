package com.example.feedmememes.ActivitiesAndFragments.activityAndFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feedmememes.ActivitiesAndFragments.models.trendingMemesResponse;
import com.example.feedmememes.ActivitiesAndFragments.viewModels.memesViewModel;
import com.example.feedmememes.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView= findViewById(R.id.textViewId);
        memesViewModel memesViewModel= ViewModelProviders.of(this).get(com.example.feedmememes.ActivitiesAndFragments.viewModels.memesViewModel.class);
        memesViewModel.getTrending().observe(this, new Observer<trendingMemesResponse>() {
            @Override
            public void onChanged(trendingMemesResponse trendingMemesResponse) {
                if(trendingMemesResponse.getData().size()>0){
                    Toast.makeText(MainActivity.this, "size great than 0", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
