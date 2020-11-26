package com.alim.relhan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alim.relhan.MyObject.Towns;
import com.alim.relhan.R;

import java.util.List;

public class TownAdapter extends ArrayAdapter<Towns> {


    private LayoutInflater inflater;
    private int layout;
    private List<Towns> list_towns;

    public TownAdapter(Context context, int resource, List<Towns> list_towns) {
        super(context, resource, list_towns);
        this.list_towns = list_towns;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view=  inflater.inflate(this.layout, parent, false);

        ImageView img_town = (ImageView) view.findViewById(R.id.img_town);
        TextView txv_town = (TextView) view.findViewById(R.id.txv_town);


        Towns towns = list_towns.get(position);

        img_town.setImageResource(towns.getImage());
        txv_town.setText(towns.getName());

        return view;
    }


}
