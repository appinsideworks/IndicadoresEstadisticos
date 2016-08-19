package com.appinsideworks.indicadoresestadisticos.Controlador.Local;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appinsideworks.indicadoresestadisticos.Modelo.Indicador;
import com.appinsideworks.indicadoresestadisticos.R;
import com.appinsideworks.indicadoresestadisticos.Vista.DetailsActivity;

import java.util.List;


public class AdapterIndicador extends RecyclerView.Adapter<AdapterIndicador.IndicadorHolder> {


    private List<Indicador> list;
    private LayoutInflater inflater;
    private Context context;

    public AdapterIndicador(List<Indicador> list, Context context) {
        this.list = list;
        this.context = context;
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


    class IndicadorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View container;
        TextView nombre;
        Toolbar toolbar;

        public IndicadorHolder(View itemView) {
            super(itemView);
            container = (View) itemView.findViewById(R.id.cont_item_root);
            nombre = (TextView) itemView.findViewById(R.id.lbl_indicador);
            toolbar = (Toolbar) itemView.findViewById(R.id.cardToolbar);

            toolbar.inflateMenu(R.menu.menu_card);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Indicador indicador = list.get(getAdapterPosition());
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("Titulo", indicador.getNombre());
            context.startActivity(intent);

        }
    }

}
