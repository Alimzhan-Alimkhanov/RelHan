package com.alim.relhan.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alim.relhan.MyObject.Jobs;
import com.alim.relhan.R;

import java.util.List;

public class Fragment_Favorite_Adapter extends RecyclerView.Adapter<Fragment_Favorite_Adapter.ViewHolder> {



    private LayoutInflater inflater;
    private List<Jobs> list_jobs;

    private final OnJobsItemClickListener listener;

    private SharedPreferences sharedPreferences;


    public  Fragment_Favorite_Adapter(Context context, List<Jobs> list_jobs, OnJobsItemClickListener listener) {
        this.list_jobs = list_jobs;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public Fragment_Favorite_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_findjob, parent, false);
        return new Fragment_Favorite_Adapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final Fragment_Favorite_Adapter.ViewHolder holder, int position) {

        final Jobs jobs = list_jobs.get(position);

        holder.txv_title.setText(jobs.getTitle());
        holder.txv_name.setText(jobs.getPlace());


        holder.txv_college.setText(jobs.getKnow_level());


        holder.txv_skill.setText(jobs.getExpert());
        holder.txv_salary.setText(jobs.getSalary());
        holder.txv_locate.setText(jobs.getTown());
        holder.txv_time.setText(jobs.getTime());
        holder.txv_date.setText(jobs.getDate());
        holder.img_star.setImageResource(R.drawable.staractive);
        holder.bind(jobs,listener);

    }


    @Override
    public int getItemCount() { return list_jobs.size(); }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txv_title,txv_name,txv_college,txv_skill,txv_salary,txv_locate,txv_time,txv_date;
        final ImageView img_star;
        ViewHolder(View view){
            super(view);
            txv_title = (TextView) view.findViewById(R.id.txv_title);
            txv_name = (TextView) view.findViewById(R.id.txv_name);
            txv_college = (TextView) view.findViewById(R.id.txv_college);
            txv_skill = (TextView) view.findViewById(R.id.txv_skill);
            txv_salary = (TextView) view.findViewById(R.id.txv_salary);
            txv_locate = (TextView) view.findViewById(R.id.txv_locate);
            txv_time = (TextView) view.findViewById(R.id.txv_time);
            txv_date = (TextView) view.findViewById(R.id.txv_date);
            img_star = (ImageView) view.findViewById(R.id.img_star);
        }
        public void bind(final Jobs jobs, final OnJobsItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(jobs);
                }
            });
        }

    }

}
