package com.alim.relhan.Helper;

import android.support.annotation.NonNull;

import com.alim.relhan.MyObject.User;
import com.alim.relhan.MyObject.UserInfo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FbsUserInfo {

    private FirebaseDatabase mDataBase;
    private DatabaseReference mRefenceUsers;
    private List<UserInfo> list_user_info = new ArrayList<>();

    public FbsUserInfo() {
        mDataBase = FirebaseDatabase.getInstance();
        mRefenceUsers = mDataBase.getReference("User_info");
    }

    public interface DataStatus{
        void DataIsLoaded(List<UserInfo> list_users_info,List<String> keys);
        void DataIsInserted();
    }


    public void readUsersInfo(final FbsUserInfo.DataStatus dataStatus){
        mRefenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_user_info.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    UserInfo userinfo = keyNode.getValue(UserInfo.class);
                    list_user_info.add(userinfo);
                }

                dataStatus.DataIsLoaded(list_user_info,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void writeuser(UserInfo userInfo,final FbsUserInfo.DataStatus dataStatus)
    {
        String key = mRefenceUsers.push().getKey();
        mRefenceUsers.child(key).setValue(userInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsInserted();
                    }
                });
    }



}
