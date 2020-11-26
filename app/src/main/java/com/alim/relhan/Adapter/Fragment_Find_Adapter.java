package com.alim.relhan.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alim.relhan.Activitys.MainActivity;
import com.alim.relhan.Activitys.MapsActivity;
import com.alim.relhan.Fragment.Fragment_Find;
import com.alim.relhan.Helper.FbsDatabaseUserHelper;
import com.alim.relhan.MyObject.Contact;
import com.alim.relhan.MyObject.Jobs;
import com.alim.relhan.MyObject.User;
import com.alim.relhan.R;

import java.util.List;

public class Fragment_Find_Adapter extends RecyclerView.Adapter<Fragment_Find_Adapter.ViewHolder> {


    private LayoutInflater inflater;
    private List<Jobs> list_jobs;

    private final OnJobsItemClickListener listener;

    private SharedPreferences sharedPreferences;

    final String email;

    public final Context g_context;

    String addtofavoritetext="";
    String removefavoritetext="";

    public  Fragment_Find_Adapter (Context context, List<Jobs> list_jobs,OnJobsItemClickListener listener) {
        this.list_jobs = list_jobs;
        g_context = context;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        email = sharedPreferences.getString("Email","null");
    }

    @Override
    public Fragment_Find_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_findjob, parent, false);
        if (MainActivity.language.equals("ru"))
        {
            addtofavoritetext="Добавленно в избранные";
            removefavoritetext="Удаленно их избранных";

        }else {
            addtofavoritetext="Таңдаулылларға қосылды";
            removefavoritetext="Таңдаулылардан алынды";
        }
        return new Fragment_Find_Adapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final Fragment_Find_Adapter.ViewHolder holder, int position) {

        final Jobs jobs = list_jobs.get(position);

        holder.txv_title.setText(jobs.getTitle());
        holder.txv_name.setText(jobs.getPlace());


        holder.txv_college.setText(jobs.getKnow_level());


        holder.txv_skill.setText(jobs.getExpert());
        holder.txv_salary.setText(jobs.getSalary());
        holder.txv_locate.setText(jobs.getTown());
        holder.txv_time.setText(jobs.getTime());
        holder.txv_date.setText(jobs.getDate());

        for(String id: getfavorites(MainActivity.gl_favorite))
        {
            if(id.equals(jobs.getId()))
            {
                holder.img_star.setImageResource(R.drawable.staractive);
            }
        }

        holder.img_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int n = 0;

                if(holder.img_star.getDrawable().getConstantState() == g_context.getDrawable(R.drawable.starnoactive).getConstantState()) {

                    Log.d("Testlog", "ACTIVE");

                    new FbsDatabaseUserHelper().updatefavorite(MainActivity.user_key, MainActivity.gl_favorite + " " + jobs.getId());

                    n = 1;


                    Toast.makeText(g_context, addtofavoritetext, Toast.LENGTH_SHORT).show();
                }

                if(holder.img_star.getDrawable().getConstantState() == g_context.getDrawable(R.drawable.staractive).getConstantState()) {
                    Log.d("Testlog", "NOACTIVE");

                    holder.img_star.setImageResource(R.drawable.staractive);

                    String[] favs = getfavorites(MainActivity.gl_favorite);

                    for(int i=0;i<favs.length;i++)
                    {
                        if(favs[i].equals(jobs.getId()))
                        {
                            favs[i] = "";
                            MainActivity.gl_favorite = TextUtils.join(" ",favs);
                            new FbsDatabaseUserHelper().updatefavorite(MainActivity.user_key, MainActivity.gl_favorite);
                        }
                    }


                    n = 2;

                    Toast.makeText(g_context, removefavoritetext, Toast.LENGTH_SHORT).show();
                }


                switch (n)
                {
                    case 1:
                        holder.img_star.setImageResource(R.drawable.staractive);
                        break;

                    case 2:
                        holder.img_star.setImageResource(R.drawable.starnoactive);
                        break;
                }



            }
        });

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

    public String[] getfavorites(String fav)
    {

        String[] array = fav.split(" ");
        return  array;
    }


}
