package com.alim.relhan.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alim.relhan.Activitys.MainActivity;
import com.alim.relhan.MyObject.Kindergarten;
import com.alim.relhan.MyObject.School;
import com.alim.relhan.R;

import java.util.List;

public class Kindergartens_Adapter extends RecyclerView.Adapter<Kindergartens_Adapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Kindergarten> list_kindergartens;

    private final OnKindergartenItemClickListener listener;

    private Context _context;

    public  Kindergartens_Adapter (Context context, List<Kindergarten> list_kindergartens,OnKindergartenItemClickListener listener) {
        this.list_kindergartens = list_kindergartens;
        this.inflater = LayoutInflater.from(context);
        this._context = context;
        this.listener = listener;
    }

    @Override
    public Kindergartens_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_kindergarten, parent, false);
        return new Kindergartens_Adapter.ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(Kindergartens_Adapter.ViewHolder holder, int position) {
        Kindergarten kindergarten = list_kindergartens.get(position);

        if(MainActivity.language.equals("ru")) {
            holder.txv_title.setText(kindergarten.getTitle_ru());
            holder.txv_street_1.setText(_context.getString(R.string.street_ru));
            holder.txv_freeplace_1.setText(_context.getString(R.string.freeplace_ru));

            if(kindergarten.getFreeplace().equals("0"))
            {
                holder.txv_freeplace_2.setText("нет");
            }else {holder.txv_freeplace_2.setText(kindergarten.getFreeplace()+" место");}

        }else
        {
            holder.txv_title.setText(kindergarten.getTitle_kz());
            holder.txv_street_1.setText(_context.getString(R.string.street_kz));
            holder.txv_freeplace_1.setText(_context.getString(R.string.freeplace_kz));

            if(kindergarten.getFreeplace().equals("0"))
            {
                holder.txv_freeplace_2.setText("жоқ");
            }else {holder.txv_freeplace_2.setText(kindergarten.getFreeplace()+" орын");}

        }

        holder.txv_street_2.setText(kindergarten.getStreet());
        holder.txv_number_2.setText(kindergarten.getNumber());


        holder.bind(kindergarten,listener);

    }


    @Override
    public int getItemCount() { return list_kindergartens.size(); }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txv_title,txv_street_1,txv_street_2,txv_number_1,txv_number_2,txv_freeplace_1,txv_freeplace_2;
        ViewHolder(View view){
            super(view);
            txv_title = (TextView) view.findViewById(R.id.txv_title);
            txv_street_1 = (TextView) view.findViewById(R.id.txv_street_1);
            txv_street_2 = (TextView) view.findViewById(R.id.txv_street_2);
            txv_number_1 = (TextView) view.findViewById(R.id.txv_number_1);
            txv_number_2 = (TextView) view.findViewById(R.id.txv_number_2);
            txv_freeplace_1 = (TextView) view.findViewById(R.id.txv_freeplace_1);
            txv_freeplace_2 = (TextView) view.findViewById(R.id.txv_freeplace_2);
        }
        public void bind(final Kindergarten item, final OnKindergartenItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onKindergartenItemClick(item);
                }
            });
        }

    }
}
