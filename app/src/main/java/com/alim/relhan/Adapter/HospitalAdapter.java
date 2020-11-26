package com.alim.relhan.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alim.relhan.Activitys.MainActivity;
import com.alim.relhan.MyObject.Hospital;
import com.alim.relhan.MyObject.Kindergarten;
import com.alim.relhan.MyObject.School;
import com.alim.relhan.R;

import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Hospital> list_hospitals;

    private Context _context;

    private OnHospitalItemClickListener listener;

    public  HospitalAdapter (Context context, List<Hospital> list_hospitals,OnHospitalItemClickListener listener) {
        this.list_hospitals = list_hospitals;
        this.inflater = LayoutInflater.from(context);
        _context = context;
        this.listener = listener;
    }

    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_hospital, parent, false);
        return new HospitalAdapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(HospitalAdapter.ViewHolder holder, int position) {

        Hospital hospital = list_hospitals.get(position);

        if(MainActivity.language.equals("ru")) {
            holder.txv_title.setText(hospital.getTitle_ru());
            holder.txv_street_1.setText(_context.getString(R.string.street_ru));
            holder.txv_doctor_1.setText(_context.getString(R.string.doctor_ru));
        }else {
            holder.txv_title.setText(hospital.getTitle_kz());
            holder.txv_street_1.setText(_context.getString(R.string.street_kz));
            holder.txv_doctor_1.setText(_context.getString(R.string.doctor_kz));
        }

        holder.txv_street_2.setText(hospital.getStreet());
        holder.txv_doctor_2.setText(hospital.getContact());
        holder.txv_number_2.setText(hospital.getNumber());

        holder.bind(hospital,listener);

    }


    @Override
    public int getItemCount() { return list_hospitals.size(); }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txv_title,txv_street_1,txv_street_2,txv_doctor_1,txv_doctor_2,txv_number_1,txv_number_2;
        ViewHolder(View view){
            super(view);
            txv_title = (TextView) view.findViewById(R.id.txv_title);
            txv_street_1 = (TextView) view.findViewById(R.id.txv_street_1);
            txv_street_2 = (TextView) view.findViewById(R.id.txv_street_2);
            txv_doctor_1 = (TextView) view.findViewById(R.id.txv_doctor_1);
            txv_doctor_2 = (TextView) view.findViewById(R.id.txv_doctor_2);
            txv_number_1 = (TextView) view.findViewById(R.id.txv_number_1);
            txv_number_2 = (TextView) view.findViewById(R.id.txv_number_2);
        }
        public void bind(final Hospital item, final OnHospitalItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onHospitalItemClick(item);
                }
            });
        }

    }
}
