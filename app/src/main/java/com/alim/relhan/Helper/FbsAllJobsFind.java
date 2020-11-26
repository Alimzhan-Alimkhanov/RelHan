package com.alim.relhan.Helper;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.alim.relhan.Adapter.Fragment_Find_Adapter;
import com.alim.relhan.Fragment.Fragment_Find;
import com.alim.relhan.MyObject.Jobs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FbsAllJobsFind {


    private FirebaseDatabase mDataBase;
    private DatabaseReference mRefence;
    private List<Jobs> listjobs = new ArrayList<>();


    public FbsAllJobsFind() {
          mDataBase = FirebaseDatabase.getInstance();
    }

    public interface DataStatus{
        void DataIsLoaded(List<Jobs> listjobs,List<String> keys);
    }


    private  String ltown="";
    private  String ltype="";

    public void readAll (final DataStatus dataStatus) {

        ltown = Fragment_Find.town;
        ltype = Fragment_Find.type;

        if(ltown.equals("Любой")){ltown="";}
        if(ltown.equals("Кезкелген")){ltown="";}
        if(ltown.equals("Усть-Каменогорск")){ltown="Ust";}
        if(ltown.equals("Өскемен")){ltown="Ust";}
        if(ltown.equals("Риддер")){ltown="Ridder";}
        if(ltown.equals("Семей")){ltown="Semei";}

        if(ltype.equals("Любой")){ltype="";}
        if(ltype.equals("Кезкелген")){ltype="";}

//        Log.d("Testlog","1FINDTEX "+Fragment_Find.findtext);
//        Log.d("Testlog","2TOWN "+ltown);
//        Log.d("Testlog","3TYPE"+ltype);
//        Log.d("Testlog","4SALARY "+Fragment_Find.salary);

        if(!Fragment_Find.findtext.equals("") && ltown.equals("") && ltype.equals("") && Fragment_Find.salary.equals(""))
        {
            Log.d("Testlog","1000" + Fragment_Find.findtext);
            mRefence = mDataBase.getReference(Fragment_Find.langDataBase);
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
                            if(jobs.getTitle().toUpperCase().contains(Fragment_Find.findtext.toUpperCase())) {
                                listjobs.add(jobs);
                            }
                        }
                    }
                    dataStatus.DataIsLoaded(listjobs,keys);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }



        if(Fragment_Find.findtext.equals("") && !ltown.equals("") && ltype.equals("") && Fragment_Find.salary.equals(""))
        {
            mRefence = mDataBase.getReference(Fragment_Find.langDataBase+"/"+ltown);
            Log.d("Testlog","0100");
            Log.d("Testlog","OnlyTown: " + Fragment_Find.town+" "+ltown);

            mRefence.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listjobs.clear();
                    List<String> keys = new ArrayList<>();
                    for (DataSnapshot keynode : dataSnapshot.getChildren()) {
                            keys.add(keynode.getKey());
                            Jobs jobs =  keynode.getValue(Jobs.class);
                            listjobs.add(jobs);
                    }
                    dataStatus.DataIsLoaded(listjobs,keys);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }






        if(Fragment_Find.findtext.equals("") && ltown.equals("") && !ltype.equals("") && Fragment_Find.salary.equals("")) {
            Log.d("Testlog","0010");
            Log.d("Testlog","OnlyType: " + ltype);

            mRefence = mDataBase.getReference(Fragment_Find.langDataBase);
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
                            if(jobs.getType().toUpperCase().contains(Fragment_Find.type.toUpperCase())) {
                                listjobs.add(jobs);
                            }
                        }
                    }
                    dataStatus.DataIsLoaded(listjobs,keys);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

        if(Fragment_Find.findtext.equals("") && ltown.equals("") && ltype.equals("") && !Fragment_Find.salary.equals("")) {
            Log.d("Testlog","0001");
            mRefence = mDataBase.getReference(Fragment_Find.langDataBase);
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

                            if(getsalary(jobs.getSalary())>=Integer.valueOf(Fragment_Find.salary)) {
                                listjobs.add(jobs);
                            }
                        }
                    }
                    dataStatus.DataIsLoaded(listjobs,keys);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }



        if(!Fragment_Find.findtext.equals("") && !ltown.equals("") && ltype.equals("") && Fragment_Find.salary.equals(""))
        {
            mRefence = mDataBase.getReference(Fragment_Find.langDataBase+"/"+ltown);
            Log.d("Testlog","1100");
            Log.d("Testlog","OnlyTown: " + Fragment_Find.town+" "+ltown);

            mRefence.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listjobs.clear();
                    List<String> keys = new ArrayList<>();
                    for (DataSnapshot keynode : dataSnapshot.getChildren()) {
                        keys.add(keynode.getKey());
                        Jobs jobs =  keynode.getValue(Jobs.class);
                        if(jobs.getTitle().toUpperCase().contains(Fragment_Find.findtext.toUpperCase())) {
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

        if(!Fragment_Find.findtext.equals("") && ltown.equals("") && !ltype.equals("") && Fragment_Find.salary.equals(""))
        {
            Log.d("Testlog","1010");
            mRefence = mDataBase.getReference(Fragment_Find.langDataBase);
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
                            if(jobs.getType().toUpperCase().contains(Fragment_Find.type.toUpperCase()) &&  jobs.getTitle().toUpperCase().contains(Fragment_Find.findtext.toUpperCase())) {
                                listjobs.add(jobs);
                            }

                        }
                    }
                    dataStatus.DataIsLoaded(listjobs,keys);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

        if(!Fragment_Find.findtext.equals("") && ltown.equals("") && ltype.equals("") && !Fragment_Find.salary.equals("")) {
            Log.d("Testlog","1001");
            mRefence = mDataBase.getReference(Fragment_Find.langDataBase);
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

                            if(jobs.getTitle().toUpperCase().contains(Fragment_Find.findtext.toUpperCase()) && getsalary(jobs.getSalary())>=Integer.valueOf(Fragment_Find.salary)) {
                                listjobs.add(jobs);
                            }
                        }
                    }
                    dataStatus.DataIsLoaded(listjobs,keys);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


        if(!Fragment_Find.findtext.equals("") && ltown.equals("") && !ltype.equals("") && !Fragment_Find.salary.equals(""))
        {
            Log.d("Testlog","1011");
            mRefence = mDataBase.getReference(Fragment_Find.langDataBase);
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

                            if(jobs.getTitle().toUpperCase().contains(Fragment_Find.findtext.toUpperCase()) && jobs.getType().toUpperCase().contains(Fragment_Find.type.toUpperCase()) && getsalary(jobs.getSalary())>=Integer.valueOf(Fragment_Find.salary))
                            {
                                listjobs.add(jobs);
                            }
                        }
                    }
                    dataStatus.DataIsLoaded(listjobs,keys);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }


        if(!Fragment_Find.findtext.equals("") && !ltown.equals("") && !ltype.equals("") && Fragment_Find.salary.equals(""))
        {
            Log.d("Testlog","1110");
            mRefence = mDataBase.getReference(Fragment_Find.langDataBase+"/"+ltown);
            Log.d("Testlog","OnlyTown: " + Fragment_Find.town+" "+ltown);

            mRefence.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listjobs.clear();
                    List<String> keys = new ArrayList<>();
                    for (DataSnapshot keynode : dataSnapshot.getChildren()) {
                        keys.add(keynode.getKey());
                        Jobs jobs =  keynode.getValue(Jobs.class);
                        if(jobs.getTitle().toUpperCase().contains(Fragment_Find.findtext.toUpperCase())&&jobs.getType().toUpperCase().contains(Fragment_Find.type.toUpperCase())) {
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

        if(!Fragment_Find.findtext.equals("") && !ltown.equals("") && ltype.equals("") && !Fragment_Find.salary.equals(""))
        {
            Log.d("Testlog","1101");
            mRefence = mDataBase.getReference(Fragment_Find.langDataBase+"/"+ltown);
            Log.d("Testlog","OnlyTown: " + Fragment_Find.town+" "+ltown);

            mRefence.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listjobs.clear();
                    List<String> keys = new ArrayList<>();
                    for (DataSnapshot keynode : dataSnapshot.getChildren()) {
                        keys.add(keynode.getKey());
                        Jobs jobs =  keynode.getValue(Jobs.class);
                        if(jobs.getTitle().toUpperCase().contains(Fragment_Find.findtext.toUpperCase()) && getsalary(jobs.getSalary())>=Integer.valueOf(Fragment_Find.salary)) {
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


        if(Fragment_Find.findtext.equals("") && !ltown.equals("") && !ltype.equals("") && Fragment_Find.salary.equals(""))
        {
            Log.d("Testlog","0110");
            mRefence = mDataBase.getReference(Fragment_Find.langDataBase+"/"+ltown);
            Log.d("Testlog","OnlyTown: " + Fragment_Find.town+" "+ltown);

            mRefence.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listjobs.clear();
                    List<String> keys = new ArrayList<>();
                    for (DataSnapshot keynode : dataSnapshot.getChildren()) {
                        keys.add(keynode.getKey());
                        Jobs jobs =  keynode.getValue(Jobs.class);
                        if(jobs.getType().toUpperCase().contains(Fragment_Find.type.toUpperCase())) {
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

        if(Fragment_Find.findtext.equals("") && !ltown.equals("") && ltype.equals("") && !Fragment_Find.salary.equals(""))
        {
            Log.d("Testlog","0101");
            mRefence = mDataBase.getReference(Fragment_Find.langDataBase+"/"+ltown);
            Log.d("Testlog","1FINDTEX "+Fragment_Find.findtext);
            Log.d("Testlog","2TOWN "+ltown);
            Log.d("Testlog","3TYPE"+ltype);
            Log.d("Testlog","4SALARY "+Fragment_Find.salary);
            Log.d("Testlog","5LangDatabase "+Fragment_Find.langDataBase);
            ///og.d("Testlog","TownandSalary: " +Fragment_Find.langDataBase +ltown);

            mRefence.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listjobs.clear();
                    List<String> keys = new ArrayList<>();
                    for (DataSnapshot keynode : dataSnapshot.getChildren()) {
                        keys.add(keynode.getKey());
                        Jobs jobs =  keynode.getValue(Jobs.class);
                        if(getsalary(jobs.getSalary())>=Integer.valueOf(Fragment_Find.salary)) {
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

        if(Fragment_Find.findtext.equals("") && ltown.equals("") && !ltype.equals("") && !Fragment_Find.salary.equals(""))
        {
            Log.d("Testlog","0001");
            mRefence = mDataBase.getReference(Fragment_Find.langDataBase);
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
                            if(jobs.getType().toUpperCase().contains(Fragment_Find.type.toUpperCase()) && getsalary(jobs.getSalary())>=Integer.valueOf(Fragment_Find.salary)) {
                                listjobs.add(jobs);
                            }

                        }
                    }
                    dataStatus.DataIsLoaded(listjobs,keys);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }


        if(Fragment_Find.findtext.equals("") && !ltown.equals("") && !ltype.equals("") && !Fragment_Find.salary.equals(""))
        {
            Log.d("Testlog","0111");
            mRefence = mDataBase.getReference(Fragment_Find.langDataBase+"/"+ltown);
            Log.d("Testlog","OnlyTown: " + Fragment_Find.town+" "+ltown);

            mRefence.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listjobs.clear();
                    List<String> keys = new ArrayList<>();
                    for (DataSnapshot keynode : dataSnapshot.getChildren()) {
                        keys.add(keynode.getKey());
                        Jobs jobs =  keynode.getValue(Jobs.class);
                        if(jobs.getType().toUpperCase().contains(Fragment_Find.type.toUpperCase()) && getsalary(jobs.getSalary())>=Integer.valueOf(Fragment_Find.salary)) {
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





        if(!Fragment_Find.findtext.equals("") && !ltown.equals("") && !ltype.equals("") && !Fragment_Find.salary.equals(""))
        {
            Log.d("Testlog","1111");
            mRefence = mDataBase.getReference(Fragment_Find.langDataBase+"/"+ltown);
            Log.d("Testlog","OnlyTown: " + Fragment_Find.town+" "+ltown);

            mRefence.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    listjobs.clear();
                    List<String> keys = new ArrayList<>();
                    for (DataSnapshot keynode : dataSnapshot.getChildren()) {
                        keys.add(keynode.getKey());
                        Jobs jobs =  keynode.getValue(Jobs.class);
                        if(jobs.getTitle().toUpperCase().contains(Fragment_Find.findtext.toUpperCase())&&jobs.getType().toUpperCase().contains(Fragment_Find.type.toUpperCase()) && getsalary(jobs.getSalary())>=Integer.valueOf(Fragment_Find.salary)) {
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


        if(Fragment_Find.findtext.equals("") && ltown.equals("") && ltype.equals("") && Fragment_Find.salary.equals("")) {
            Log.d("Testlog","0000");
            mRefence = mDataBase.getReference(Fragment_Find.langDataBase);
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





    public void readAllWihoutFilter(final DataStatus dataStatus)
    {
        mRefence = mDataBase.getReference(Fragment_Find.langDataBase);
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


    public int getsalary(String str_salary)
    {
        int int_salary;

        if(str_salary.contains("-"))
        {
            String[] array = str_salary.split("-");
            int_salary = Integer.valueOf(array[0]);
        }else {
            int_salary = Integer.valueOf(str_salary);
        }

        return  int_salary;
    }

}
