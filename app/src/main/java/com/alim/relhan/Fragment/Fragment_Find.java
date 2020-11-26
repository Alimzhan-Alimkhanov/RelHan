package com.alim.relhan.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.alim.relhan.Activitys.FIlterActivity;
import com.alim.relhan.Activitys.JobsActivity;
import com.alim.relhan.Activitys.MainActivity;
import com.alim.relhan.Adapter.Fragment_Find_Adapter;
import com.alim.relhan.Adapter.OnJobsItemClickListener;
import com.alim.relhan.Helper.FbsAllJobsFind;
import com.alim.relhan.Helper.FbsDatabaseUserHelper;
import com.alim.relhan.MyObject.Jobs;
import com.alim.relhan.MyObject.User;
import com.alim.relhan.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Find extends Fragment {


    public Fragment_Find() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        _context = context;
    }


    public static Context _context;

    public static Jobs gloab_jobs;

    public static RecyclerView recyclerView;
    public static Fragment_Find_Adapter fragment_find_adapter;
    public static TextView txvnotfound;

    public static EditText editfind;
    private TextView textfilter;



    public static String langDataBase="JobsRU";

    public static String findtext="";
    public static String town="";
    public static String type="";
    public static String salary="";

    public static int sorttype = 0;

    public static String textnotfoun;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__find, container, false);
        setAppbar(view);
        editfind = view.findViewById(R.id.edx_search);
        textfilter = view.findViewById(R.id.txv_filter);
        txvnotfound = view.findViewById(R.id.txvnotfound);

        if (MainActivity.language.equals("ru"))
        {
            langDataBase="JobsRU";
            txvnotfound.setText("Ничего не найдено");
        }else
        {
            langDataBase="JobsKZ";
            txvnotfound.setText("Тукте табылмады");
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recview_find);


        new FbsAllJobsFind().readAllWihoutFilter(new FbsAllJobsFind.DataStatus() {
            @Override
            public void DataIsLoaded(List<Jobs> listjobs, List<String> keys) {
                Log.d("Testlog","SizeAll: "+listjobs.size());
                fragment_find_adapter = new Fragment_Find_Adapter(_context, listjobs, new OnJobsItemClickListener() {
                    @Override
                    public void onItemClick(Jobs jobs) {

                        gloab_jobs = jobs;
                        Intent intent = new Intent(getContext(), JobsActivity.class);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(fragment_find_adapter);
            }
        });



        editfind.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    findtext = v.getText().toString();
                    new FbsAllJobsFind().readAll(new FbsAllJobsFind.DataStatus() {
                        @Override
                        public void DataIsLoaded(List<Jobs> listjobs, List<String> keys) {
                            txvnotfound.setVisibility(View.GONE);
//                            Log.d("Testlog","Filter: "+Fragment_Find.findtext);
//                            Log.d("Testlog","Filter: "+Fragment_Find.town);
//                            Log.d("Testlog","Filter: "+Fragment_Find.type);
//                            Log.d("Testlog","Filter: "+Fragment_Find.salary);

                            if(Fragment_Find.sorttype!=0)
                            {
                                listjobs = FIlterActivity.getsortedlistjob(listjobs);
                                Log.d("Testlog","Sorttype!=0 " +listjobs.size());
                            }else {Log.d("Testlog","Sorttype==0 "+listjobs.size());}

                            fragment_find_adapter = new Fragment_Find_Adapter(_context, listjobs, new OnJobsItemClickListener() {
                                @Override
                                public void onItemClick(Jobs jobs) {
                                    gloab_jobs = jobs;
                                    Intent intent = new Intent(getContext(), JobsActivity.class);
                                    startActivity(intent);
                                }
                            });
                            recyclerView.setAdapter(fragment_find_adapter);

                            if (listjobs.size() == 0) {
                                txvnotfound.setVisibility(View.VISIBLE);
                            }


                        }
                    });

                    return true;
                }
                return false;
            }
        });


        textfilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FIlterActivity.class);
                startActivity(intent);
                findtext = editfind.getText().toString();
            }
        });

        return view;
    }





    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void setAppbar(View view)
    {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        DrawerLayout drawer = ((AppCompatActivity)getActivity()).findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

}
