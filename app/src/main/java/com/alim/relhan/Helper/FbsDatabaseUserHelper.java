package com.alim.relhan.Helper;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.alim.relhan.MyObject.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FbsDatabaseUserHelper {

    private FirebaseDatabase mDataBase;
    private DatabaseReference mRefenceUsers;
    private List<User> users = new ArrayList<>();


    public FbsDatabaseUserHelper() {
        mDataBase = FirebaseDatabase.getInstance();
        mRefenceUsers = mDataBase.getReference("Users");
    }

    public interface DataStatus{
        void DataIsLoaded(List<User> users,List<String> keys);
        void DataIsInserted();
    }


    public void readUsers(final  DataStatus dataStatus){
        mRefenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    User user = keyNode.getValue(User.class);
                    users.add(user);
                }

                dataStatus.DataIsLoaded(users,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void writeuser(User user,final DataStatus dataStatus)
    {
        String key = mRefenceUsers.push().getKey();
        mRefenceUsers.child(key).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }


    public interface FavoriteStatus
    {
        void DataIsLoaded(String lastvalue);
    }


    public void getfavorite(String key,final  FavoriteStatus favoriteStatus)
    {
        DatabaseReference mluserref = mDataBase.getReference("Users/"+key);


        mluserref.child("favorite").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String lastvalue = dataSnapshot.getValue(String.class);
                favoriteStatus.DataIsLoaded(lastvalue);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void updatefavorite(String key,String fav)
    {
        DatabaseReference mluserref = mDataBase.getReference("Users/"+key);
        mluserref.child("favorite").setValue(fav);
    }




}
