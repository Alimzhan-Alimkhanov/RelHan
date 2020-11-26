package com.alim.relhan.Helper;

import android.support.annotation.NonNull;

import com.alim.relhan.MyObject.Jobs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FbsTownJobs {

    private FirebaseDatabase mDataBase;
    private DatabaseReference mRefence;
    private List<Jobs> listjobs = new ArrayList<>();




    public FbsTownJobs(String langDataBase, String town) {
        mDataBase = FirebaseDatabase.getInstance();
        mRefence = mDataBase.getReference(langDataBase+"/"+ town);
    }

    public interface DataStatus{
        void DataIsLoaded(List<Jobs> listjobs,List<String> keys);;
    }


    public void readJobs (final FbsTownJobs.DataStatus dataStatus){
        mRefence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  listjobs.clear();
                  List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());

                }
                 dataStatus.DataIsLoaded(listjobs,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
