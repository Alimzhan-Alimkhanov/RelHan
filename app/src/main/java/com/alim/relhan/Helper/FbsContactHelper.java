package com.alim.relhan.Helper;

import android.support.annotation.NonNull;

import com.alim.relhan.MyObject.Contact;
import com.alim.relhan.MyObject.News;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FbsContactHelper  {

    private FirebaseDatabase mDataBase;
    private DatabaseReference mRefenceContacts;
    private List<Contact> listcontacts = new ArrayList<>();


    public FbsContactHelper() {
        mDataBase = FirebaseDatabase.getInstance();
        mRefenceContacts = mDataBase.getReference("Contacts");
    }

    public interface DataStatus{
        void DataIsLoaded(List<Contact> listcontacts,List<String> keys);
    }


    public void readContacts(final FbsContactHelper.DataStatus dataStatus){
        mRefenceContacts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listcontacts.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    Contact contact = keyNode.getValue(Contact.class);
                    listcontacts.add(contact);
                }

                dataStatus.DataIsLoaded(listcontacts,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
