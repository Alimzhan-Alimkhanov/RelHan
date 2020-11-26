package com.alim.relhan.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.alim.relhan.Fragment.Fragment_InfoTown;
import com.alim.relhan.Fragment.Fragment_SocObjects;
import com.alim.relhan.R;

public class TownsTabsAdapter extends FragmentPagerAdapter {


    private final String[] tabs;
    private String sel_town;
    private String link;

    public TownsTabsAdapter(String sel_town,String link,FragmentManager fm,String[] tabs) {
        super(fm);
        this.tabs = tabs;
        this.sel_town = sel_town;
        this.link = link;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: return new Fragment_InfoTown(link);
            case 1: return new Fragment_SocObjects(sel_town);
            default: return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
