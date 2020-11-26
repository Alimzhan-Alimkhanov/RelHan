package com.alim.relhan.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alim.relhan.Adapter.Jobs_Item_Adapter;
import com.alim.relhan.Fragment.Fragment_Find;
import com.alim.relhan.MyObject.JobsTitle;
import com.alim.relhan.MyObject.TitleName;
import com.alim.relhan.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class JobsActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    Jobs_Item_Adapter jobs_item_adapter;

    TextView txv_title;

    ImageView img_map;

    List<TitleName> list_name_title = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_job);

        txv_title = (TextView) findViewById(R.id.txv_title);
        txv_title.setText(Fragment_Find.gloab_jobs.getTitle());
        img_map = (ImageView) findViewById(R.id.img_map);

        setAppbar();
        setdata();

        recyclerView = (RecyclerView) findViewById(R.id.recview_jobs);
        jobs_item_adapter = new Jobs_Item_Adapter(getApplicationContext(),list_name_title);
        recyclerView.setAdapter(jobs_item_adapter);

        img_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                intent.putExtra("Title",Fragment_Find.gloab_jobs.getWork_street());
                intent.putExtra("COR1",Fragment_Find.gloab_jobs.getCor1());
                intent.putExtra("COR2",Fragment_Find.gloab_jobs.getCor2());
                startActivity(intent);
            }
        });

    }

    public void setdata()
    {
        if(MainActivity.language.equals("ru")) {
            list_name_title.add(new TitleName("Регион", Fragment_Find.gloab_jobs.getTown()));
            list_name_title.add(new TitleName("Сфера работы:", Fragment_Find.gloab_jobs.getType()));
            list_name_title.add(new TitleName("Предприятия:", Fragment_Find.gloab_jobs.getPlace()));
            list_name_title.add(new TitleName("Оплата Труда:", Fragment_Find.gloab_jobs.getSalary() + "тг"));
            list_name_title.add(new TitleName("Время:", Fragment_Find.gloab_jobs.getTime()));
            list_name_title.add(new TitleName("Адрес Работы:", Fragment_Find.gloab_jobs.getWork_street()));
            list_name_title.add(new TitleName("Опыт Работы:", Fragment_Find.gloab_jobs.getExpert()));
            list_name_title.add(new TitleName("Специальные Навыки:", Fragment_Find.gloab_jobs.getSpec_skill()));
            list_name_title.add(new TitleName("Оброзоания:", Fragment_Find.gloab_jobs.getKnow_level()));
            list_name_title.add(new TitleName("Личностные харектеристик:", Fragment_Find.gloab_jobs.getSoc_skillz()));
            list_name_title.add(new TitleName("Допольнительные Требовании:", Fragment_Find.gloab_jobs.getAdd_per()));
            list_name_title.add(new TitleName("Количество мест:", Fragment_Find.gloab_jobs.getCount_place()));
            list_name_title.add(new TitleName("Контактное лицо:", Fragment_Find.gloab_jobs.getContact_name()));
            list_name_title.add(new TitleName("Email:", Fragment_Find.gloab_jobs.getContact_email()));
            list_name_title.add(new TitleName("Контактные Телефон\":", Fragment_Find.gloab_jobs.getContact_number()));
        }else{
            list_name_title.add(new TitleName("Аймақ", Fragment_Find.gloab_jobs.getTown()));
            list_name_title.add(new TitleName("Қызмет саласы:", Fragment_Find.gloab_jobs.getType()));
            list_name_title.add(new TitleName("Кәсіпорын:", Fragment_Find.gloab_jobs.getPlace()));
            list_name_title.add(new TitleName("Еңбекақы:", Fragment_Find.gloab_jobs.getSalary() + "тг"));
            list_name_title.add(new TitleName("Уақыт:", Fragment_Find.gloab_jobs.getTime()));
            list_name_title.add(new TitleName("Жұмыс орны:", Fragment_Find.gloab_jobs.getWork_street()));
            list_name_title.add(new TitleName("Мамандық бойынша өтілі:", Fragment_Find.gloab_jobs.getExpert()));
            list_name_title.add(new TitleName("Арнайы білімі мен дағдылары:", Fragment_Find.gloab_jobs.getSpec_skill()));
            list_name_title.add(new TitleName("Білім деңгейі:", Fragment_Find.gloab_jobs.getKnow_level()));
            list_name_title.add(new TitleName("Жалпы және икемді дағдылар:", Fragment_Find.gloab_jobs.getSoc_skillz()));
            list_name_title.add(new TitleName("Қосымша талаптар:", Fragment_Find.gloab_jobs.getAdd_per()));
            list_name_title.add(new TitleName("Бос жұмыс орындар саны:", Fragment_Find.gloab_jobs.getCount_place()));
            list_name_title.add(new TitleName("Байланыстағы тұлға:", Fragment_Find.gloab_jobs.getContact_name()));
            list_name_title.add(new TitleName("Email:", Fragment_Find.gloab_jobs.getContact_email()));
            list_name_title.add(new TitleName("Байланыс деректері:", Fragment_Find.gloab_jobs.getContact_number()));
        }

    }



    public void setAppbar()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(MainActivity.language.equals("ru")) {
            getSupportActionBar().setTitle("Работа");
        }else{getSupportActionBar().setTitle("Жұмыс");}
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("TITLE");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setTitle("Работа");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
