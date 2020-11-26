package com.alim.relhan.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alim.relhan.Activitys.JobsActivity;
import com.alim.relhan.Activitys.MainActivity;
import com.alim.relhan.Adapter.Fragment_Favorite_Adapter;
import com.alim.relhan.Adapter.OnJobsItemClickListener;
import com.alim.relhan.Helper.FbsFavoriteJobs;
import com.alim.relhan.MyObject.Jobs;
import com.alim.relhan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Favorite extends Fragment {


    public Fragment_Favorite() {
        // Required empty public constructor
    }


    private RecyclerView recview_favorites;
    private TextView txv_not_favorites;

    private String langDatabae ="JobsRU";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__favorite, container, false);

        setAppbar(view);

        if(MainActivity.language.equals("ru"))
        {
            langDatabae ="JobsRU";
        }else {
            langDatabae ="JobsKZ";
        }

        recview_favorites = (RecyclerView) view.findViewById(R.id.recview_favorites);
        txv_not_favorites = (TextView) view.findViewById(R.id.txvnotfavorites);

        new FbsFavoriteJobs(langDatabae).readAll(new FbsFavoriteJobs.DataStatus() {
            @Override
            public void DataIsLoaded(List<Jobs> listjobs, List<String> keys) {
                List<Jobs> local_jobs = new ArrayList<>();

                txv_not_favorites.setVisibility(View.GONE);

                for (Jobs jobs: listjobs)
                {
                    for (String id: getfavorites(MainActivity.gl_favorite))
                    {
                        if(id.equals(jobs.getId()))
                        {
                            local_jobs.add(jobs);
                        }
                    }
                }

                Fragment_Favorite_Adapter fragment_favorite_adapter = new Fragment_Favorite_Adapter(getContext(), local_jobs, new OnJobsItemClickListener() {
                    @Override
                    public void onItemClick(Jobs jobs) {
                        Fragment_Find.gloab_jobs = jobs;
                        Intent intent = new Intent(getContext(), JobsActivity.class);
                        startActivity(intent);
                    }
                });

                if (fragment_favorite_adapter!=null)
                {
                    recview_favorites.setAdapter(fragment_favorite_adapter);
                }

                if (local_jobs.size()==0)
                {
                    txv_not_favorites.setVisibility(View.VISIBLE);
                }

            }
        });






        return  view;
    }



    public String[] getfavorites(String fav)
    {

        String[] array = fav.split(" ");
        return  array;
    }


    public void setAppbar(View view)
    {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if(MainActivity.language.equals("ru")) {
            toolbar.setTitle(R.string.nav_favorite_ru);
        }else {toolbar.setTitle(R.string.nav_favorite_kz);}
        DrawerLayout drawer = ((AppCompatActivity)getActivity()).findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


}


