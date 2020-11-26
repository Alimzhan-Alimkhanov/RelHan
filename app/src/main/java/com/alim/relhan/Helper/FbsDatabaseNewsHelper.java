package com.alim.relhan.Helper;

import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.alim.relhan.Activitys.MainActivity;
import com.alim.relhan.MyObject.News;
import com.alim.relhan.MyObject.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FbsDatabaseNewsHelper {

    private FirebaseDatabase mDataBase;
    private DatabaseReference mRefenceUsers;
    private List<News> listnews = new ArrayList<>();



    public FbsDatabaseNewsHelper() {
        mDataBase = FirebaseDatabase.getInstance();
        if(MainActivity.language.equals("ru")) {
            mRefenceUsers = mDataBase.getReference("News");
        }else {
            mRefenceUsers = mDataBase.getReference("News_Kz");
        }
    }

    public interface DataStatus{
        void DataIsLoaded(List<News> listnews,List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }


    public void readNews(final FbsDatabaseNewsHelper.DataStatus dataStatus){
        mRefenceUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listnews.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: dataSnapshot.getChildren())
                {
                    keys.add(keyNode.getKey());
                    News news = keyNode.getValue(News.class);
                    listnews.add(news);
                }

                dataStatus.DataIsLoaded(listnews,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
