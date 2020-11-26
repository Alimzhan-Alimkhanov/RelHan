package com.alim.relhan.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alim.relhan.Adapter.TownsTabsAdapter;
import com.alim.relhan.MyObject.Towns;
import com.alim.relhan.R;

public class TownActivity extends AppCompatActivity {


    private String[] tabs;
    ActionBar actionBar;
    Toolbar toolbar;
    AppBarLayout appbar;
    TabLayout tabLayout;
    ViewPager viewpager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_town);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();


        Intent intent = getIntent();
        String town = intent.getStringExtra("Town");
        String sel_town = intent.getStringExtra("Sel_Town");
        String link = intent.getStringExtra("Link");
        actionBar.setTitle(town);

        tabs = new String[2];
        if(MainActivity.language.equals("ru")) {
            tabs[0] = getString(R.string.TownTabsInfo_ru);
            tabs[1] = getString(R.string.TownTabsSoc_ru);
        }else {
            tabs[0] = getString(R.string.TownTabsInfo_kz);
            tabs[1] = getString(R.string.TownTabsSoc_kz);
        }


        appbar = (AppBarLayout) findViewById(R.id.appbar);
        tabLayout = new TabLayout(this);
        tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
        viewpager = findViewById(R.id.viewpager);
        viewpager.setAdapter(new TownsTabsAdapter(sel_town,link,getSupportFragmentManager(),tabs));
        tabLayout.setupWithViewPager(viewpager);
        appbar.addView(tabLayout);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed(); // Or what ever action you want here.
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
