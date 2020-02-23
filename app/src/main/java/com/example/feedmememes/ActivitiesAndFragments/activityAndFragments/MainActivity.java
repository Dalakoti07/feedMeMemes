package com.example.feedmememes.ActivitiesAndFragments.activityAndFragments;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.amitshekhar.DebugDB;
import com.example.feedmememes.ActivitiesAndFragments.adapter.mainPageAdapter;
import com.example.feedmememes.ActivitiesAndFragments.models.constantsClass;
import com.example.feedmememes.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
//    adb forward tcp:8080 tcp:8080 for starting debugging in android app
    String urls="";
    private int primaryDarkCode=R.string.primaryDarkOne,primaryCode=R.string.primaryOne;
    @SuppressLint("ResourceAsColor")
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
        isStoragePermissionGranted();
        Log.d(constantsClass.logTag," url for debugging db is "+ DebugDB.getAddressLog());
//        set status bar and app bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            Log.d("commonLog","color fro status bar is "+getString(R.string.primaryDarkOne));
            getRandomColorsCodes();
            window.setStatusBarColor(Color.parseColor(getString(primaryDarkCode)));
            Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(Color.parseColor(getResources().getString(primaryCode))));
        }
    }

    private void getRandomColorsCodes() {
        ArrayList<Integer> primaryColorsList=new ArrayList<>();
        ArrayList<Integer> primaryDarkColorList= new ArrayList<>();

        // fill the primaryColor
        primaryColorsList.add(R.string.primaryOne);primaryColorsList.add(R.string.primaryTwo);primaryColorsList.add(R.string.primaryThree);primaryColorsList.add(R.string.primaryFour);
        primaryColorsList.add(R.string.primaryFive);primaryColorsList.add(R.string.primarySix);primaryColorsList.add(R.string.primarySeven);
        // fill the primaryDark
        primaryDarkColorList.add(R.string.primaryDarkOne);primaryDarkColorList.add(R.string.primaryDarkTwo);primaryDarkColorList.add(R.string.primaryDarkThree);
        primaryDarkColorList.add(R.string.primaryDarkFour);primaryDarkColorList.add(R.string.primaryDarkFive);primaryDarkColorList.add(R.string.primaryDarkSix);primaryDarkColorList.add(R.string.primaryDarkSeven);

        // generate random number and set color
        Random rand = new Random();
        int index =rand.nextInt(7);
        primaryCode=primaryColorsList.get(index);
        primaryDarkCode=primaryDarkColorList.get(index);
        constantsClass.currentPrimaryColor=getResources().getString(primaryCode);
        constantsClass.currentDarkPrimaryColor=getResources().getString(primaryDarkCode);
    }

    private   boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(constantsClass.logTag,"Permission is granted");
                return true;
            } else {
                Log.v(constantsClass.logTag,"Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(constantsClass.logTag,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v("commonTag","Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }

}
