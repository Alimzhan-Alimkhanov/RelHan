package com.alim.relhan.Helper;

import android.support.annotation.NonNull;
import android.widget.ScrollView;

import com.alim.relhan.MyObject.Hospital;
import com.alim.relhan.MyObject.Kindergarten;
import com.alim.relhan.MyObject.News;
import com.alim.relhan.MyObject.School;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FbsDatabaseSchKisHos  {

    private FirebaseDatabase mDataBase;
    private DatabaseReference mRefenceUsers;
    private List<School> listschols = new ArrayList<>();
    private List<Kindergarten> liskindergartens = new ArrayList<>();
    private List<Hospital> listhospitals = new ArrayList<>();


    public FbsDatabaseSchKisHos(String town,String socobjects) {
        mDataBase = FirebaseDatabase.getInstance();
        mRefenceUsers = mDataBase.getReference(town+"ScKindHos"+"/"+socobjects);
    }

    public interface DataStatus{
        void DataSchoolsIsLoaded(List<School> listschools,List<String> keys);
        void DataKindergartensIsLoaded(List<Kindergarten> listkindergartens,List<String> keys);
        void DataHospitalsIsLoaded(List<Hospital> listhospitals,List<String> keys);
    }


    public void readSchools(final FbsDatabaseSchKisHos.DataStatus dataStatus){
        mRefenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listschols.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    School school = keyNode.getValue(School.class);
                    listschols.add(school);
                }

                dataStatus.DataSchoolsIsLoaded(listschols,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void readKindergartens(final FbsDatabaseSchKisHos.DataStatus dataStatus){
        mRefenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                liskindergartens.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    Kindergarten kindergarten = keyNode.getValue(Kindergarten.class);
                    liskindergartens.add(kindergarten);
                }

                dataStatus.DataKindergartensIsLoaded(liskindergartens,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void readHospitals(final FbsDatabaseSchKisHos.DataStatus dataStatus){
        mRefenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listhospitals.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    Hospital hospital = keyNode.getValue(Hospital.class);
                    listhospitals.add(hospital);
                }

                dataStatus.DataHospitalsIsLoaded(listhospitals,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
