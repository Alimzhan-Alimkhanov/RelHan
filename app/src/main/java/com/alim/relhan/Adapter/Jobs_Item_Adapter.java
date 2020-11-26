package com.alim.relhan.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alim.relhan.MyObject.Jobs;
import com.alim.relhan.MyObject.JobsTitle;
import com.alim.relhan.MyObject.TitleName;
import com.alim.relhan.R;

import java.util.List;

public class Jobs_Item_Adapter  extends  RecyclerView.Adapter<Jobs_Item_Adapter.ViewHolder>
{

    private LayoutInflater inflater;
    private List<TitleName> list_title_name;


    public  Jobs_Item_Adapter (Context context,List<TitleName> list_title_name) {
        this.list_title_name = list_title_name;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public Jobs_Item_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_act_jobs, parent, false);
        return new Jobs_Item_Adapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(Jobs_Item_Adapter.ViewHolder holder, int position) {

        holder.txv_title.setText(list_title_name.get(position).getTitle());
        holder.txv_name.setText(list_title_name.get(position).getName());
    }


    @Override
    public int getItemCount() { return list_title_name.size(); }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txv_title,txv_name;
        ViewHolder(View view){
            super(view);
            txv_title = (TextView) view.findViewById(R.id.txv_title);
            txv_name = (TextView) view.findViewById(R.id.txv_name);;
        }

    }
}
