package com.oneroofit.taskapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.oneroofit.taskapplication.city.CityAdapter;
import com.oneroofit.taskapplication.city.CityModel;

public class MainActivity extends AppCompatActivity  {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=(Toolbar) findViewById(R.id.maintoolbar);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        viewPager =(ViewPager)findViewById(R.id.ViewPager);

        setSupportActionBar(toolbar);
        setupViewPager(viewPager);
        getSupportActionBar().setTitle(R.string.toolbar_title);


        tabLayout.setupWithViewPager(viewPager);

    }
    private  void setupViewPager(ViewPager viewPager){

        ViewPagerAdapter viewPagerAdapter =new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ALL(),"ALL");
        viewPagerAdapter.addFragment(new Chicago(),"Chicago");
        viewPagerAdapter.addFragment(new NewYork(),"NewYork");
        viewPagerAdapter.addFragment(new LosAngeles(),"Los Angeles");
        viewPager.setAdapter(viewPagerAdapter);


    }



}
