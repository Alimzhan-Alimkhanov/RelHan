package com.alim.relhan.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alim.relhan.Activitys.MainActivity;
import com.alim.relhan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Info_App extends Fragment {


    public Fragment_Info_App() {
        // Required empty public constructor
    }


    TextView txv_info_app;
    TextView txv_ver;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__info__app, container, false);

        setAppbar(view);

        txv_info_app = (TextView) view.findViewById(R.id.txv_app_info);
        txv_ver = (TextView) view.findViewById(R.id.txv_version);


        if (MainActivity.language.equals("ru"))
        {
            txv_info_app.setText(getString(R.string.info_app_ru));
            txv_ver.setText(getString(R.string.app_ver_ru));

        }else{
            txv_info_app.setText(getString(R.string.info_app_kz));
            txv_ver.setText(getString(R.string.app_ver_kz));
        }



        return  view;
    }

    public void setAppbar(View view)
    {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if(MainActivity.language.equals("ru")) {
            toolbar.setTitle(R.string.nav_infoapp_ru);
        }else {toolbar.setTitle(R.string.nav_infoapp_kz);}
        DrawerLayout drawer = ((AppCompatActivity)getActivity()).findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


}
