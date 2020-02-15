package com.example.feedmememes.ActivitiesAndFragments.activityAndFragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.feedmememes.ActivitiesAndFragments.adapter.mainPageAdapter;
import com.example.feedmememes.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    String urls="";
    private String TAG="commonTag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager;
        viewPager = findViewById(R.id.pager);
        mainPageAdapter mainPageAdapter= new mainPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mainPageAdapter);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }


}
