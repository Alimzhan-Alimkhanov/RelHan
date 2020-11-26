package com.alim.relhan.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alim.relhan.Activitys.MainActivity;
import com.alim.relhan.MyObject.News;
import com.alim.relhan.MyObject.Towns;
import com.alim.relhan.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>  {

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    StorageReference ref;

    private LayoutInflater inflater;
    private List<News> lnews;

    private String img_link="";


    private final OnItemClickListener listener;

    public NewsAdapter(Context context, List<News> lnews,OnItemClickListener listener) {
        this.lnews = lnews;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
        storageReference = firebaseStorage.getInstance().getReference();

        if(MainActivity.language.equals("ru"))
        {
            img_link="NewsImages/";
        }else{
            img_link="NewsImagesKz/";
        }

    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsAdapter.ViewHolder holder, int position) {

        News news = lnews.get(position);


        String nstr = news.getTitle();
        if(nstr.length()>37){nstr = nstr.substring(0,37)+"...";}

        holder.txv_title.setText(nstr);
        holder.txv_date.setText(news.getDate());

        ref = storageReference.child(img_link+news.getId_img()+".jpg");
        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.img_news);
            }
        });


        holder.bind(news, listener);
    }




    @Override
    public int getItemCount() {
        return lnews.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_news;
        final TextView txv_title,txv_date;
        ViewHolder(View view){
            super(view);
            img_news= (ImageView) view.findViewById(R.id.imv_news);
            txv_title = (TextView) view.findViewById(R.id.txv_title);
            txv_date = (TextView) view.findViewById(R.id.txv_date);
        }

        public void bind(final News news, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(news);
                }
            });
        }


    }




}
