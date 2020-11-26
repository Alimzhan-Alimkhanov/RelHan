package com.alim.relhan.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alim.relhan.Activitys.MainActivity;
import com.alim.relhan.Adapter.NewsAdapter;
import com.alim.relhan.Adapter.OnItemClickListener;
import com.alim.relhan.Helper.FbsDatabaseNewsHelper;
import com.alim.relhan.MyObject.News;
import com.alim.relhan.Activitys.NewsActivity;
import com.alim.relhan.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_News extends Fragment {



    Context _context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
    }

    public Fragment_News() {
        // Required empty public constructor
    }


    private RecyclerView recyclerView;
    private ActionBar actionBar;



    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment__news, container, false);


        setAppbar(view);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewnews);

        Log.d("Testlag","NewsFragmentCreated");

        new FbsDatabaseNewsHelper().readNews(new FbsDatabaseNewsHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<News> listnews, List<String> keys) {
                Log.d("Testlog",listnews.get(0).getTitle());
               NewsAdapter newsadapter = new NewsAdapter(getContext(), listnews, new OnItemClickListener() {
                   @Override
                   public void onItemClick(News news) {
                       Intent intent = new Intent(getActivity(), NewsActivity.class);
                       intent.putExtra("title",news.title);
                       intent.putExtra("text",news.text);
                       intent.putExtra("id_img",news.id_img);
                       intent.putExtra("count_img",news.count_img);
                       intent.putExtra("date",news.date);
                       intent.putExtra("link_res",news.link_res);;
                       startActivity(intent);
                   }
               });

               if(newsadapter!=null) {
                   recyclerView.setAdapter(newsadapter);
               }
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });


        return  view;
    }

    public void setAppbar(View view)
    {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if(MainActivity.language.equals("ru")) {
            toolbar.setTitle(R.string.nav_news_ru);
        }else {toolbar.setTitle(R.string.nav_news_kz);}
        DrawerLayout drawer = ((AppCompatActivity)getActivity()).findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

}
