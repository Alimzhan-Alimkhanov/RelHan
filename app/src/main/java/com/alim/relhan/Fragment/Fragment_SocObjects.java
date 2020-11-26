package com.alim.relhan.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.alim.relhan.Activitys.MainActivity;
import com.alim.relhan.Activitys.MapsActivity;
import com.alim.relhan.Adapter.HospitalAdapter;
import com.alim.relhan.Adapter.Kindergartens_Adapter;
import com.alim.relhan.Adapter.MySpinnerAdapter;
import com.alim.relhan.Adapter.OnHospitalItemClickListener;
import com.alim.relhan.Adapter.OnKindergartenItemClickListener;
import com.alim.relhan.Adapter.OnSchoolItemClickListener;
import com.alim.relhan.Adapter.School_Adapter;
import com.alim.relhan.Helper.FbsDatabaseSchKisHos;
import com.alim.relhan.MyObject.Hospital;
import com.alim.relhan.MyObject.Item_Spinner;
import com.alim.relhan.MyObject.Kindergarten;
import com.alim.relhan.MyObject.School;
import com.alim.relhan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_SocObjects extends Fragment {




    List<Item_Spinner> list_spinner = new ArrayList<>();

    private final String sel_town;

    public Fragment_SocObjects(String  sel_town) {
        this.sel_town = sel_town;
    }

    private Spinner spinner;
    private SpinnerAdapter spinnerAdapter;

    String snackbartext="";
    String snackbarbtntext="";


    private RecyclerView recyclerview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment__soc_objects, container, false);

        spinner = (Spinner) view.findViewById(R.id.spinner);
        recyclerview = (RecyclerView)  view.findViewById(R.id.recyclerview_socobjects);

        setData();
        spinnerAdapter = new MySpinnerAdapter(getContext(),list_spinner);
        spinner.setAdapter(spinnerAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, final View view, int position, long id) {
                Log.d("Testlog","SelectedTown(SpinerSel) " + sel_town );
                switch (position)
                {
                    case 0:
                     //   Log.d("Testlog","Spinner 0 "+sel_town);
                        new FbsDatabaseSchKisHos(sel_town,"Schools").readSchools(new FbsDatabaseSchKisHos.DataStatus() {
                            @Override
                            public void DataSchoolsIsLoaded(List<School> listschools, List<String> keys) {
                                School_Adapter school_adapter = new School_Adapter(getContext(), listschools, new OnSchoolItemClickListener() {
                                    @Override
                                    public void onSchoolItemClick(final School school) {

                                        Snackbar.make(view,"Показать на Карте ?",Snackbar.LENGTH_LONG).setAction("ДА", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(getContext(), MapsActivity.class);
                                                intent.putExtra("Title",school.getTitle_ru());
                                                intent.putExtra("COR1",school.getCor1());
                                                intent.putExtra("COR2",school.getCor2());
                                                startActivity(intent);
                                            }
                                        }).show();


                                    }
                                });
                                recyclerview.setAdapter(school_adapter);
                            }

                            @Override
                            public void DataKindergartensIsLoaded(List<Kindergarten> listkindergartens, List<String> keys) {

                            }

                            @Override
                            public void DataHospitalsIsLoaded(List<Hospital> listhospitals, List<String> keys) {

                            }
                        });

                    break;

                    case 1:
                        Log.d("Testlog","Spinner 1");
                        new FbsDatabaseSchKisHos(sel_town,"Kindergartens").readKindergartens(new FbsDatabaseSchKisHos.DataStatus() {
                            @Override
                            public void DataSchoolsIsLoaded(List<School> listschools, List<String> keys) {

                            }

                            @Override
                            public void DataKindergartensIsLoaded(List<Kindergarten> listkindergartens, List<String> keys) {
                                Kindergartens_Adapter kindergartens_adapter = new Kindergartens_Adapter(getContext(), listkindergartens, new OnKindergartenItemClickListener() {
                                    @Override
                                    public void onKindergartenItemClick(final Kindergarten kindergarten) {
                                        Snackbar.make(view,"Показать на Карте ?",Snackbar.LENGTH_LONG).setAction("ДА", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(getContext(), MapsActivity.class);
                                                intent.putExtra("Title",kindergarten.getTitle_ru());
                                                intent.putExtra("COR1",kindergarten.getCor1());
                                                intent.putExtra("COR2",kindergarten.getCor2());
                                                startActivity(intent);
                                            }
                                        }).show();
                                    }
                                });
                                recyclerview.setAdapter(kindergartens_adapter);
                            }

                            @Override
                            public void DataHospitalsIsLoaded(List<Hospital> listhospitals, List<String> keys) {

                            }
                        });

                    break;


                    case 2:
                        Log.d("Testlog","Spinner 2");
                        new FbsDatabaseSchKisHos(sel_town,"Hospilats").readHospitals(new FbsDatabaseSchKisHos.DataStatus() {
                            @Override
                            public void DataSchoolsIsLoaded(List<School> listschools, List<String> keys) {
                            }

                            @Override
                            public void DataKindergartensIsLoaded(List<Kindergarten> listkindergartens, List<String> keys) {

                            }

                            @Override
                            public void DataHospitalsIsLoaded(List<Hospital> listhospitals, List<String> keys) {
                                HospitalAdapter hospitalAdapter = new HospitalAdapter(getContext(), listhospitals, new OnHospitalItemClickListener() {
                                    @Override
                                    public void onHospitalItemClick(final Hospital hospital) {
                                        Snackbar.make(view,"Показать на Карте ?",Snackbar.LENGTH_LONG).setAction("ДА", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Intent intent = new Intent(getContext(), MapsActivity.class);
                                                intent.putExtra("Title",hospital.getTitle_ru());
                                                intent.putExtra("COR1",hospital.getCor1());
                                                intent.putExtra("COR2",hospital.getCor2());
                                                startActivity(intent);
                                            }
                                        }).show();
                                    }
                                });
                                recyclerview.setAdapter(hospitalAdapter);
                            }

                        });
                    break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });











        return  view;
    }

    public void setData()
    {
        if(MainActivity.language.equals("ru")) {
            list_spinner.add(new Item_Spinner(getString(R.string.Schols_ru)));
            list_spinner.add(new Item_Spinner(getString(R.string.Kindergartens_ru)));
            list_spinner.add(new Item_Spinner(getString(R.string.Hospitals_ru)));
            snackbartext = getString(R.string.SnackbarMapShowtext_ru);
            snackbarbtntext = "ДА";
        }else{
            list_spinner.add(new Item_Spinner(getString(R.string.Schols_kz)));
            list_spinner.add(new Item_Spinner(getString(R.string.Kindergartens_kz)));
            list_spinner.add(new Item_Spinner(getString(R.string.Hospitals_kz)));
            snackbartext = getString(R.string.SnackbarMapShowtext_kz);
            snackbarbtntext = "ЙА";
        }
    }


}
