package com.appinsideworks.indicadoresestadisticos.Controlador.Local;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appinsideworks.indicadoresestadisticos.Modelo.Indicador;
import com.appinsideworks.indicadoresestadisticos.R;

import java.util.List;

/**
 * Created by ramon_a on 8/8/16.
 */
public class AdapterIndicador extends RecyclerView.Adapter<AdapterIndicador.IndicadorHolder> {


    private List<Indicador> list;
    private LayoutInflater inflater;

    public AdapterIndicador(List<Indicador> list, Context context) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public IndicadorHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_indicador, parent, false);
        return new IndicadorHolder(view);
    }

    @Override
    public void onBindViewHolder(IndicadorHolder holder, int position) {

        Indicador item = list.get(position);
        holder.nombre.setText(item.getNombre());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class IndicadorHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        View container;

        public IndicadorHolder(View itemView) {
            super(itemView);
            nombre = (TextView)itemView.findViewById(R.id.lbl_indicador);
            container = (View)itemView.findViewById(R.id.cont_item_root);
        }
    }
}
