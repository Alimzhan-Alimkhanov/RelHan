package com.alim.relhan.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.alim.relhan.Activitys.MainActivity;
import com.alim.relhan.Activitys.TownActivity;
import com.alim.relhan.Adapter.NewsAdapter;
import com.alim.relhan.Adapter.TownAdapter;
import com.alim.relhan.MyObject.Towns;
import com.alim.relhan.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Town extends Fragment {

    private static List<Towns> list_of_towns = new ArrayList<>();
    private Context _context;
    private ListView lsview_towns;

    public  static boolean check = false;





    public Fragment_Town() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        _context=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_town, container, false);

        setAppbar(view);

        lsview_towns = (ListView) view.findViewById(R.id.listView);

        if (!check){setData(); check=true;}

        TownAdapter adapter = new TownAdapter(getContext(),R.layout.item_towns,list_of_towns);
        lsview_towns.setAdapter(adapter);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Towns towns = list_of_towns.get(position);

                Intent intent = new Intent(getContext(), TownActivity.class);
                intent.putExtra("Town",towns.getName());
                intent.putExtra("Sel_Town",towns.getSel_name());
                intent.putExtra("Link",towns.getLink());
                startActivity(intent);
            }
        };

        lsview_towns.setOnItemClickListener(itemClickListener);



        return  view;
    }


    public void setData()
    {
        if(MainActivity.language.equals("ru")) {
            list_of_towns.add(new Towns(getResources().getString(R.string.Uststr_ru), "Ust", R.drawable.ust_kaman, getString(R.string.Ustlink_ru)));
            list_of_towns.add(new Towns(getResources().getString(R.string.Semeistr_ru), "Semei", R.drawable.semei, getString(R.string.Semeilink_kz)));
        }else{
            list_of_towns.add(new Towns(getResources().getString(R.string.Uststr_kz), "Ust", R.drawable.ust_kaman, getString(R.string.Ustlink_kz)));
            list_of_towns.add(new Towns(getResources().getString(R.string.Semeistr_kz), "Semei", R.drawable.semei, getString(R.string.Semeilink_kz)));
        }
    }

    public void setAppbar(View view)
    {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if(MainActivity.language.equals("ru")) {
            toolbar.setTitle(R.string.nav_town_ru);
        }else{toolbar.setTitle(R.string.nav_town_kz);}
        DrawerLayout drawer = ((AppCompatActivity)getActivity()).findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }





}
