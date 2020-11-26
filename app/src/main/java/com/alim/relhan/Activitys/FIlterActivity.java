package com.alim.relhan.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.alim.relhan.Adapter.Fragment_Find_Adapter;
import com.alim.relhan.Adapter.OnJobsItemClickListener;
import com.alim.relhan.Fragment.Fragment_Find;
import com.alim.relhan.Helper.FbsAllJobsFind;
import com.alim.relhan.MyObject.Jobs;
import com.alim.relhan.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FIlterActivity extends AppCompatActivity {



    private String[] towns;
    private String[] types = new String[12];
    private String[] sort_types = new String[3];



    private Spinner spr_town;
    private Spinner spr_types;
    private Spinner spr_sort;

    private Button btn_find;
    private EditText edx_find;
    private EditText edx_salary;

    private TextView txv_find_text;
    private TextView txv_spr_town;
    private TextView txv_spr_types;
    private TextView txv_salary_1;
    private TextView txv_salary_2;
    private TextView txv_sort1;





    ArrayAdapter<String> adapter_towns;
    ArrayAdapter<String> adapter_types;
    ArrayAdapter<String> adapter_sorts;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        spr_town = (Spinner) findViewById(R.id.spr_town);
        spr_types = (Spinner) findViewById(R.id.spr_types);
        spr_sort = (Spinner) findViewById(R.id.spr_sort);
        btn_find = (Button) findViewById(R.id.btn_find);
        edx_find = (EditText) findViewById(R.id.edx_find);
        edx_salary = (EditText) findViewById(R.id.edx_salary);

        txv_find_text = (TextView) findViewById(R.id.edx_text);
        txv_spr_town = (TextView) findViewById(R.id.txv_spr_town);
        txv_spr_types = (TextView) findViewById(R.id.txv_scope_job);
        txv_salary_1 = (TextView) findViewById(R.id.txv_salary_1);
        txv_salary_2 = (TextView) findViewById(R.id.txv_salary_2);
        txv_sort1 = (TextView) findViewById(R.id.txv_sort_1);

        settext();

        edx_find.setText(Fragment_Find.findtext);
        edx_salary.setText(Fragment_Find.salary);


        adapter_towns= new ArrayAdapter<String>(this, R.layout.item_snr_town, towns);
        adapter_towns.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spr_town.setAdapter(adapter_towns);
        if(Fragment_Find.town.equals(""))
        {
            spr_town.setSelection(0);
        }else {spr_town.setSelection(Arrays.asList(towns).indexOf(Fragment_Find.town));}



        adapter_types= new ArrayAdapter<String>(this, R.layout.item_snr_town, types);
        adapter_types.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spr_types.setAdapter(adapter_types);
        if(Fragment_Find.type.equals(""))
        {
            spr_types.setSelection(0);
        }else {spr_types.setSelection(Arrays.asList(types).indexOf(Fragment_Find.type));}


        adapter_sorts = new ArrayAdapter<String>(this,R.layout.item_snr_town,sort_types);
        adapter_sorts.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spr_sort.setAdapter(adapter_sorts);
        spr_sort.setSelection(Fragment_Find.sorttype);



        spr_town.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Fragment_Find.town = towns[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spr_types.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Fragment_Find.type = types[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spr_sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Fragment_Find.sorttype = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        btn_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment_Find.findtext = edx_find.getText().toString();
                Fragment_Find.salary = edx_salary.getText().toString();
                Fragment_Find.editfind.setText(Fragment_Find.findtext);



                new FbsAllJobsFind().readAll(new FbsAllJobsFind.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Jobs> listjobs, List<String> keys) {
//                        Log.d("Testlog","Filter: "+Fragment_Find.findtext);
//                        Log.d("Testlog","Filter: "+Fragment_Find.town);
//                        Log.d("Testlog","Filter: "+Fragment_Find.type);
//                        Log.d("Testlog","Filter: "+Fragment_Find.salary);

                        if(Fragment_Find.sorttype!=0)
                        {
                            listjobs = getsortedlistjob(listjobs);
                            Log.d("Testlog","Sorttype!=0 " +listjobs.size());
                        }else {Log.d("Testlog","Sorttype==0 "+listjobs.size());}

                        Fragment_Find.txvnotfound.setVisibility(View.GONE);
                        Fragment_Find.fragment_find_adapter = new Fragment_Find_Adapter(Fragment_Find._context, listjobs, new OnJobsItemClickListener() {
                            @Override
                            public void onItemClick(Jobs jobs) {
                                Fragment_Find.gloab_jobs = jobs;
                                Intent intent = new Intent(Fragment_Find._context, JobsActivity.class);
                                startActivity(intent);
                            }
                        });
                        Fragment_Find.recyclerView.setAdapter(Fragment_Find.fragment_find_adapter);
                       // Log.d("Testlog","Click CLick");
                        if (listjobs.size() == 0) {
                            Fragment_Find.txvnotfound.setVisibility(View.VISIBLE);
                        }
                    }
                });
                onBackPressed();
            }
        });


    }


    public void settext()
    {
        if(MainActivity.language.equals("ru"))
        {
            txv_find_text.setText(getString(R.string.txvfind_ru));
            txv_spr_town.setText(getString(R.string.txvtown_ru));
            txv_spr_types.setText(getString(R.string.txvtype_ru));
            txv_salary_1.setText(getString(R.string.txvsalary_ru));
            txv_salary_2.setText(getString(R.string.txvsalary2_ru));
            txv_sort1.setText(getString(R.string.txvsort_ru));
            btn_find.setText(getString(R.string.btnfind_ru));


            towns = new String[4];
            towns[0]="Любой";
            towns[1]="Усть-Каменогорск";
            towns[2]="Риддер";
            towns[3]="Семей";


            types[0] = "Любой";
            types[1] = "Образование";
            types[2] = "Гостиничное дело";
            types[3] = "Транспорт/Логистика";
            types[4] = "Финансы";
            types[5] = "Рестораны, фастфуд";
            types[6] = "Обслуживающий персонал";
            types[7] = "Медицина";
            types[8] = "Продажи";
            types[9] = "Строительство";
            types[10] = "IT, телекоммуникации, связь, электроника";
            types[11] = "Промышленность, производство";


            sort_types[0]="Без сортировки";
            sort_types[1]="Зарплата по возрастанию";
            sort_types[2]="Зарплата по убыванию";



        }else{
            txv_find_text.setText(getString(R.string.txvfind_kz));
            txv_spr_town.setText(getString(R.string.txvtown_kz));
            txv_spr_types.setText(getString(R.string.txvtype_kz));
            txv_salary_1.setText(getString(R.string.txvsalary_kz));
            txv_sort1.setText(getString(R.string.txvsort_kz));
            txv_salary_2.setText(getString(R.string.txvsalary2_kz));
            btn_find.setText(getString(R.string.btnfind_kz));

            towns = new String[4];
            towns[0]="Кезкелген";
            towns[1]="Өскемен";
            towns[2]="Риддер";
            towns[3]="Семей";


            types[0] = "Кезкелген";
            types[1] = "Білім";
            types[2] = "Туризм, қонақ үй ісі";
            types[3] = "Көлік тасымалы";
            types[4] = "Қаржы";
            types[5] = "Мейрамханалар, фастфуд";
            types[6] = "Қызмет көрсетуші персонал";
            types[7] = "Медицина";
            types[8] = "Сатылым";
            types[9] = "Құрылыс";
            types[10] = "ІТ, телекоммуникация, байланыс, электроника";
            types[11] = "Өнеркәсіп, өндіріс";


            sort_types[0]="Сұрыптаусыз";
            sort_types[1]="Жалақы өсіумен";
            sort_types[2]="Жалақы төмендеумен";
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Фильтры");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            onBackPressed();
        }


        return super.onOptionsItemSelected(item);
    }

    public static  List<Jobs> getsortedlistjob(List<Jobs> list)
    {

        Jobs per_jobs = null;

        if(Fragment_Find.sorttype==1) {
            Log.d("Testlog","Sort 1");
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size()-1; j++) {
                    if (getsalary(list.get(j).getSalary()) > getsalary(list.get(j + 1).getSalary())) {
                        per_jobs = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, per_jobs);
                    }
                }
            }
        }

        if(Fragment_Find.sorttype==2) {
            for (int i = 0; i < list.size(); i++) {
                Log.d("Testlog","Sort 2");
                for (int j = 0; j < list.size()-1; j++) {
                    if (getsalary(list.get(j).getSalary()) < getsalary(list.get(j + 1).getSalary())) {
                        per_jobs = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, per_jobs);
                    }
                }
            }
        }

        return  list;
    }



    public static  int getsalary(String str_salary)
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
