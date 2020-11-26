package com.alim.relhan.Activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.alim.relhan.Fragment.Fragment_Contact;
import com.alim.relhan.Fragment.Fragment_Favorite;
import com.alim.relhan.Fragment.Fragment_Find;
import com.alim.relhan.Fragment.Fragment_InfoTown;
import com.alim.relhan.Fragment.Fragment_Info_App;
import com.alim.relhan.Fragment.Fragment_Setting;
import com.alim.relhan.Fragment.Fragment_Town;
import com.alim.relhan.Fragment.Fragment_News;
import com.alim.relhan.Helper.FbsDatabaseUserHelper;
import com.alim.relhan.Helper.FbsUserInfo;
import com.alim.relhan.MyObject.User;
import com.alim.relhan.MyObject.UserInfo;
import com.alim.relhan.R;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {




    Fragment_News fragment_news;
    Fragment_Town fragment_town;
    Fragment_Find fragment_find;
    Fragment_Favorite fragment_favorite;
    Fragment_Contact fragment_contact;
    Fragment_Setting fragment_setting;
    Fragment_Info_App fragment_info_app;

    ActionBar actionBar;

    FragmentManager fragmentManager;

    SharedPreferences sharedPreferences;

    public static String language;

    public static String gl_favorite="";
    public static String user_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String langsave = sharedPreferences.getString("Language", "null");

        if (langsave.equals("null")) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Language", "ru");
            editor.commit();
            language = "ru";
        } else {
            language = langsave;
        }


        final String email = sharedPreferences.getString("Email","null");
        final String password = sharedPreferences.getString("Password","null");


//        if(email.equals("null") || password.equals("null"))
//        {
//            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//            startActivity(intent);
//        }else{
//            Log.d("Testlog","MainAc Email: "+email);
//            Log.d("Testlog","MainAc Passw: "+password);
//            new FbsDatabaseUserHelper().readUsers(new FbsDatabaseUserHelper.DataStatus() {
//                @Override
//                public void DataIsLoaded(List<User> users, final List<String> keys) {
//                    for(int i=0;i<users.size();i++)
//                    {
//                        if(users.get(i).getEmail().equals(email)) {
//                            gl_favorite = users.get(i).getFavorite();
//                            user_key = keys.get(i);
//                        }
//                    }
//                }
//                @Override
//                public void DataIsInserted() {
//
//                }
//            });
//        }


//        new FbsUserInfo().readUsersInfo(new FbsUserInfo.DataStatus() {
//            @Override
//            public void DataIsLoaded(List<UserInfo> list_users_info, List<String> keys) {
//                Log.d("Testlog","FINDX: "+list_users_info.get(0).getFind_text());
//            }
//
//            @Override
//            public void DataIsInserted() {
//
//            }
//        });


        fragmentManager = (FragmentManager) getSupportFragmentManager();
        fragment_news = new Fragment_News();
        fragment_town = new Fragment_Town();
        fragment_find = new Fragment_Find();
        fragment_favorite = new Fragment_Favorite();
        fragment_contact = new Fragment_Contact();
        fragment_setting = new Fragment_Setting();
        fragment_info_app = new Fragment_Info_App();

        fragmentManager.beginTransaction().replace(R.id.container,fragment_news).commit();




        NavigationView navigationView = findViewById(R.id.nav_view);

         View headerView = navigationView.getHeaderView(0);
         TextView txv_email = headerView.findViewById(R.id.txv_email);
         TextView txv_logout =  headerView.findViewById(R.id.txv_logout);
         txv_email.setText(email);
         txv_logout.setOnClickListener(this);


        Menu menu = navigationView.getMenu();

        if (language.equals("ru")) {
            menu.findItem(R.id.nav_news).setTitle(getString(R.string.nav_news_ru));
            menu.findItem(R.id.nav_towns).setTitle(getString(R.string.nav_town_ru));
            menu.findItem(R.id.nav_find).setTitle(getString(R.string.nav_find_ru));
            menu.findItem(R.id.nav_favorites).setTitle(getString(R.string.nav_favorite_ru));
          //  menu.findItem(R.id.nav_contacts).setTitle(getString(R.string.nav_contact_ru));
            menu.findItem(R.id.nav_setting).setTitle(getString(R.string.nav_setting_ru));
            menu.findItem(R.id.nav_infoapp).setTitle(getString(R.string.nav_infoapp_ru));
            menu.findItem(R.id.nav_logout).setTitle(getString(R.string.nav_logout_ru));

            txv_logout.setText(getString(R.string.LogoutAcount_ru));

        }else {
            menu.findItem(R.id.nav_news).setTitle(getString(R.string.nav_news_kz));
            menu.findItem(R.id.nav_towns).setTitle(getString(R.string.nav_town_kz));
            menu.findItem(R.id.nav_find).setTitle(getString(R.string.nav_find_kz));
            menu.findItem(R.id.nav_favorites).setTitle(getString(R.string.nav_favorite_kz));
          //  menu.findItem(R.id.nav_contacts).setTitle(getString(R.string.nav_contact_kz));
            menu.findItem(R.id.nav_setting).setTitle(getString(R.string.nav_setting_kz));
            menu.findItem(R.id.nav_infoapp).setTitle(getString(R.string.nav_infoapp_kz));
            menu.findItem(R.id.nav_logout).setTitle(getString(R.string.nav_logout_kz));

            txv_logout.setText(getString(R.string.LogoutAcount_kz));
        }



        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.nav_news:
                fragmentManager.beginTransaction().replace(R.id.container,fragment_news).commit();
            break;
            case R.id.nav_towns:
                fragmentManager.beginTransaction().replace(R.id.container,fragment_town).commit();
            break;

            case R.id.nav_find:
                fragmentManager.beginTransaction().replace(R.id.container,fragment_find).commit();
            break;

            case R.id.nav_favorites:
                fragmentManager.beginTransaction().replace(R.id.container,fragment_favorite).commit();
                break;

            case R.id.nav_contacts:
                fragmentManager.beginTransaction().replace(R.id.container,fragment_contact).commit();
                break;

            case R.id.nav_setting:
                fragmentManager.beginTransaction().replace(R.id.container,fragment_setting).commit();
                break;

            case R.id.nav_infoapp:
                fragmentManager.beginTransaction().replace(R.id.container,fragment_info_app).commit();
                break;

            case  R.id.nav_logout:
                Intent intent = new Intent(MainActivity.this, Exit.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                finish();
                startActivity(intent);
            break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("Email");
        editor.remove("Password");
        editor.commit();
        Intent intent = new Intent(MainActivity.this, Exit.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        finish();
        startActivity(intent);
    }
}
