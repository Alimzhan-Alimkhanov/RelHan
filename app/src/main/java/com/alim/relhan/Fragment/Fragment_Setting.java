package com.alim.relhan.Fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alim.relhan.Activitys.MainActivity;
import com.alim.relhan.R;

public class Fragment_Setting extends Fragment implements View.OnClickListener{



    private RadioButton radiobtn_kz;
    private RadioButton radiobtn_ru;
    private TextView txv_lang;


    private SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting,container,false);
        setAppbar(view);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());


        radiobtn_kz = view.findViewById(R.id.radio_kz);
        radiobtn_ru = view.findViewById(R.id.radio_ru);
        txv_lang = view.findViewById(R.id.txv_lang);;

        if(MainActivity.language.equals("ru"))
        {
            radiobtn_ru.setChecked(true);

            txv_lang.setText(getString(R.string.setLang_ru));
            radiobtn_ru.setText(getString(R.string.RU_text_ru));
            radiobtn_kz.setText(getString(R.string.RU_text_kz));
        }else
        {
            radiobtn_kz.setChecked(true);

            txv_lang.setText(getString(R.string.setLang_kz));
            radiobtn_ru.setText(getString(R.string.KZ_text_ru));
            radiobtn_kz.setText(getString(R.string.KZ_text_kz));
        }

        radiobtn_kz.setOnClickListener(this);
        radiobtn_ru.setOnClickListener(this);

        return  view;
    }



    @Override
    public void onClick(View v) {

        RadioButton rb = (RadioButton)v;

        SharedPreferences.Editor editor = sharedPreferences.edit();


        //Хз код взят из stackoverfllow
        Intent mStartActivity = new Intent(getActivity(), MainActivity.class);
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(getActivity(), mPendingIntentId, mStartActivity,
                PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        switch (rb.getId())
        {
            case R.id.radio_kz:
                editor.putString("Language","kz");
                editor.commit();

                //Хз код взят из stackoverfllow
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
                break;
            case R.id.radio_ru:
                editor.putString("Language","ru");
                editor.commit();

                //Хз код взят из stackoverfllow
                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                System.exit(0);
                break;

        }

    }





    public void setAppbar(View view)
    {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if(MainActivity.language.equals("ru")) {
            toolbar.setTitle(R.string.nav_setting_ru);
        }else {toolbar.setTitle(R.string.nav_setting_kz);}
        DrawerLayout drawer = ((AppCompatActivity)getActivity()).findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


}
