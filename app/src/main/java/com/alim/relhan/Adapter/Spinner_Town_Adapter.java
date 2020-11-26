package com.alim.relhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.alim.relhan.R;

public class Spinner_Town_Adapter extends BaseAdapter {

    Context context;
    String[] towns;
    LayoutInflater inflater;


    public Spinner_Town_Adapter(Context context,String[] towns)
    {
        this.context = context;
        this.towns = towns;
        inflater  = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return towns.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.item_snr_town,null);
        TextView txv = (TextView) view.findViewById(R.id.txv);
        txv.setText(towns[position]);
        return view;
    }
}
