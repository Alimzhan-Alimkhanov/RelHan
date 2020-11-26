package com.alim.relhan.Fragment;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alim.relhan.Activitys.MainActivity;
import com.alim.relhan.Activitys.MapsActivity;
import com.alim.relhan.Helper.FbsContactHelper;
import com.alim.relhan.MyObject.Contact;
import com.alim.relhan.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Fragment_Contact extends Fragment {



    private String[] ltowns = {"Усть-Каменогорск","Риддер","Семей"};
    private String[] ltowns_kz = {"Өскемен","Риддер","Семей"};

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;


    private Spinner spr_contacts;
    private TextView txv_title;
    private ImageView img_contact;
    private TextView txv_contact_name;
    private TextView txv_contact_rang;
    private TextView txv_contact_street_title;
    private TextView txv_contact_street_text;
    private TextView txv_contact_number_text;
    private TextView txv_contact_email_text;
    private TextView txv_contact_number_people_title;
    private TextView txv_contact_number_people_text;
    private TextView txv_contact_number_soc_title;
    private TextView txv_contact_number_soc_text;

    private ImageView img_map;

    ArrayAdapter<String> adapter_towns;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmemt_contact, container, false);

        setAppbar(view);

        storageReference = firebaseStorage.getInstance().getReference();

        spr_contacts = (Spinner) view.findViewById(R.id.spr_contacts);
        img_contact = (ImageView) view.findViewById(R.id.img_contact);
        txv_title = (TextView) view.findViewById(R.id.txv_title);
        txv_contact_name = (TextView) view.findViewById(R.id.txv_contact_name);
        txv_contact_rang = (TextView) view.findViewById(R.id.txv_contact_rang);
        txv_contact_street_title = (TextView) view.findViewById(R.id.txv_contact_street_title);
        txv_contact_street_text = (TextView) view.findViewById(R.id.txv_contact_street_text);
        txv_contact_number_text = (TextView) view.findViewById(R.id.txv_number_text);
        txv_contact_email_text = (TextView) view.findViewById(R.id.txv_email_text);
        txv_contact_number_people_title = (TextView) view.findViewById(R.id.txv_number_people_title);
        txv_contact_number_people_text = (TextView) view.findViewById(R.id.txv_number_people_text);
        txv_contact_number_soc_title = (TextView) view.findViewById(R.id.txv_number_soc_title);
        txv_contact_number_soc_text = (TextView) view.findViewById(R.id.txv_number_soc_text);

        img_map = (ImageView) view.findViewById(R.id.img_map);




        if(MainActivity.language.equals("ru")) {
            txv_contact_street_title.setText(getString(R.string.street_ru));
            txv_contact_number_people_title.setText(getString(R.string.txvconpeople_ru));
            txv_contact_number_soc_title.setText(getString(R.string.txvconsoc_ru));
            adapter_towns = new ArrayAdapter<String>(getContext(), R.layout.item__spr_contact, ltowns);
        } else {
            txv_contact_street_title.setText(getString(R.string.street_kz));
            txv_contact_number_people_title.setText(getString(R.string.txvconpeople_kz));
            txv_contact_number_soc_title.setText(getString(R.string.txvconsoc_kz));
            adapter_towns = new ArrayAdapter<String>(getContext(), R.layout.item__spr_contact, ltowns_kz);
        }



        adapter_towns.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spr_contacts.setAdapter(adapter_towns);




        spr_contacts.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {

                new FbsContactHelper().readContacts(new FbsContactHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Contact> listcontacts, List<String> keys) {

                        for(Contact c: listcontacts)
                        {
                            if(c.getTown().equals(ltowns[position]))
                            {
                                final Contact contact = c;


                                if(MainActivity.language.equals("ru")) {
                                    txv_title.setText(contact.getName());
                                }else {txv_title.setText(contact.getName_kz());}
                                txv_contact_name.setText(contact.getRuk_name());
                                txv_contact_rang.setText(contact.getRuk_rang());
                                txv_contact_street_text.setText(contact.getStreet());
                                txv_contact_number_text.setText(contact.getNumber());
                                txv_contact_email_text.setText(contact.getEmail());
                                txv_contact_number_people_text.setText(contact.getNumber_people());
                               // Log.d("Testlog",contact.getNumber_peopple());
                                txv_contact_number_soc_text.setText(contact.getNumber_soc());

                                ref = storageReference.child("ContactsImg/"+contact.getId_img()+".jpg");
                                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        Picasso.get().load(uri).into(img_contact);
                                    }
                                });

                                img_map.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getContext(), MapsActivity.class);
                                        intent.putExtra("Title",contact.getName());
                                        intent.putExtra("COR1",contact.getCor1());
                                        intent.putExtra("COR2",contact.getCor2());
                                        startActivity(intent);
                                    }
                                });


                            }
                        }

                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return  view;
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
