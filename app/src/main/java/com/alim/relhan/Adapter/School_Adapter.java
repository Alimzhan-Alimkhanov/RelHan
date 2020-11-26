package com.alim.relhan.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alim.relhan.Activitys.MainActivity;
import com.alim.relhan.MyObject.School;
import com.alim.relhan.R;

import java.util.List;

public class School_Adapter extends RecyclerView.Adapter<School_Adapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<School> listschool;

    private final OnSchoolItemClickListener listener;

    private Context _context;

    public  School_Adapter (Context context, List<School> listschool,OnSchoolItemClickListener listener) {
        this.listschool = listschool;
        this.inflater = LayoutInflater.from(context);
        this._context = context;
        this.listener = listener;
    }

    @Override
    public School_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_school, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(School_Adapter.ViewHolder holder, int position) {
        School school = listschool.get(position);

        if(MainActivity.language.equals("ru")) {
            holder.txv_title.setText(school.getTitle_ru());
            holder.txv_street_1.setText(_context.getString(R.string.street_ru));
        }else{
            holder.txv_title.setText(school.getTitle_kz());
            holder.txv_street_1.setText(_context.getString(R.string.street_kz));
        }

        holder.txv_street_2.setText(school.getStreet());
        holder.txv_number_2.setText(school.getNumber());

        holder.bind(school,listener);
    }


    @Override
    public int getItemCount() { return listschool.size(); }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txv_title,txv_street_1,txv_street_2,txv_number_1,txv_number_2;
        ViewHolder(View view){
            super(view);
            txv_title = (TextView) view.findViewById(R.id.txv_title);
            txv_street_1 = (TextView) view.findViewById(R.id.txv_street_1);
            txv_street_2 = (TextView) view.findViewById(R.id.txv_street_2);
            txv_number_1 = (TextView) view.findViewById(R.id.txv_number_1);
            txv_number_2 = (TextView) view.findViewById(R.id.txv_number_2);
        }

        public void bind(final School item, final OnSchoolItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onSchoolItemClick(item);
                }
            });
        }


    }
}
