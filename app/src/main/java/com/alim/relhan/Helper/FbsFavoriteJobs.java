package com.alim.relhan.Helper;

import android.support.annotation.NonNull;
import android.util.Log;

import com.alim.relhan.Fragment.Fragment_Find;
import com.alim.relhan.MyObject.Jobs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FbsFavoriteJobs {

    private FirebaseDatabase mDataBase;
    private DatabaseReference mRefence;
    private List<Jobs> listjobs = new ArrayList<>();


    public FbsFavoriteJobs(String langDataBase) {
        mDataBase = FirebaseDatabase.getInstance();
        mRefence = mDataBase.getReference(langDataBase);
    }

    public interface DataStatus{
        void DataIsLoaded(List<Jobs> listjobs,List<String> keys);
    }

    public void readAll (final FbsFavoriteJobs.DataStatus dataStatus) {
        mRefence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listjobs.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyroot : dataSnapshot.getChildren()) {
                    for(DataSnapshot keychild: keyroot.getChildren())
                    {
                        keys.add(keychild.getKey());
                        Jobs jobs =  keychild.getValue(Jobs.class);
                        listjobs.add(jobs);
                    }
                }
                dataStatus.DataIsLoaded(listjobs,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
