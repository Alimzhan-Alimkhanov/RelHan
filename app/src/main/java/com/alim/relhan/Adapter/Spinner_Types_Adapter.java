package com.alim.relhan.Adapter;

import android.content.Context;
import android.os.TestLooperManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alim.relhan.R;

public class Spinner_Types_Adapter extends BaseAdapter {

    Context context;
    String[] types;
    LayoutInflater inflater;

    public Spinner_Types_Adapter(Context context,String[] types)
    {
        this.context = context;
        this.types = types;
        inflater = LayoutInflater.from(context);
    }




    @Override
    public int getCount() {
        return types.length;
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

        View view = inflater.inflate(R.layout.item_spr_types,null);

        TextView txv_type = view.findViewById(R.id.txv_type);
        txv_type.setText(types[position]);
        txv_type.setPadding(0,30,0,0);
        return view;
    }
}
