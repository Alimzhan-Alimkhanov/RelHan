package com.alim.relhan.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alim.relhan.Adapter.ImgAdapter;
import com.alim.relhan.MyObject.News;
import com.alim.relhan.R;

public class NewsActivity extends AppCompatActivity {

    News news;
    Intent intent;

    TextView txv_title;
    WebView webView;
    private ViewPager viewpager;
    private ImgAdapter imgAdapter;
    private  LinearLayout slider;
    private  int dotcount;
    private  ImageView[] dots;

    String share;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        txv_title = (TextView) findViewById(R.id.txv_title);
        webView = (WebView) findViewById(R.id.wbview);
        viewpager = (ViewPager) findViewById(R.id.pager);
        slider = (LinearLayout) findViewById(R.id.slidedots);

      //  if(MainActivity.language)

        Intent intent = getIntent();
        news = new News();
        news.setTitle(intent.getStringExtra("title"));
        news.setText(intent.getStringExtra("text"));
        news.setId_img(intent.getStringExtra("id_img"));
        news.setCount_img(intent.getStringExtra("count_img"));
        news.setDate(intent.getStringExtra("date"));
        news.setLink_res(intent.getStringExtra("link_res"));

        txv_title.setText(news.getTitle());

        String summary = "<html><body>"+news.getText()+"</body></html>";
        webView.loadData(summary, "text/html; charset=utf-8", "utf-8");

        setImages();

    }

    public void setImages()
    {
        imgAdapter = new ImgAdapter(getApplicationContext(),Integer.valueOf(news.count_img),Integer.valueOf(news.id_img));
        viewpager.setAdapter(imgAdapter);

        dotcount = imgAdapter.getCount();
        dots = new ImageView[dotcount];

        for (int i=0;i<dotcount;i++)
        {
            dots[i] = new ImageView(getApplicationContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.item_noactive));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8,0,8,0);

            slider.addView(dots[i],params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.item_active));

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {


            }

            @Override
            public void onPageSelected(int i) {

                for (int in=0;in<dotcount;in++)
                {
                    dots[in].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.item_noactive));
                }

                dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.item_active));

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if(MainActivity.language.equals("ru")) {
            getSupportActionBar().setTitle(getString(R.string.nav_news_ru));
        }else {getSupportActionBar().setTitle(getString(R.string.nav_news_kz));}


        getMenuInflater().inflate(R.menu.share_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        if(R.id.share == item.getItemId()) {

            try {
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,news.getLink_res());
                startActivity(Intent.createChooser(intent, "Поделиться"));
            } catch (Exception e) {
                Log.d("OException", e.toString());
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
