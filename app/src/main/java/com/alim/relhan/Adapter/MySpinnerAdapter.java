package com.alim.relhan.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alim.relhan.MyObject.Item_Spinner;
import com.alim.relhan.R;

import java.util.ArrayList;
import java.util.List;

public class MySpinnerAdapter extends ArrayAdapter<Item_Spinner> {

    public MySpinnerAdapter(Context context, List<Item_Spinner> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_row, parent, false
            );
        }

        TextView txv_name = convertView.findViewById(R.id.txv_name);

         Item_Spinner item_spinner = getItem(position);

        if (item_spinner!= null) {
            txv_name.setText(item_spinner.getName());
        }

        return convertView;
    }





}
