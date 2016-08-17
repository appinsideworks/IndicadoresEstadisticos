package com.appinsideworks.indicadoresestadisticos.Vista;


import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appinsideworks.indicadoresestadisticos.Controlador.Local.AdapterIndicador;
import com.appinsideworks.indicadoresestadisticos.Controlador.Local.Misc;
import com.appinsideworks.indicadoresestadisticos.Controlador.Web.DAOIndicador;
import com.appinsideworks.indicadoresestadisticos.Controlador.Web.DataDownloadListener;
import com.appinsideworks.indicadoresestadisticos.Modelo.Indicador;
import com.appinsideworks.indicadoresestadisticos.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements DataDownloadListener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private LinearLayoutManager manager;
    private SwipeRefreshLayout swipeContainer;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private AdView adView;

    private AdapterIndicador adapter;
    private AdRequest adRequest;
    private DAOIndicador daoIndicador;
    private Misc misc;

    private FragmentManager FM;
    private FragmentTransaction FT;

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Activity activity = getActivity();

        //Inicialización de variables

        misc = new Misc();
        misc.setContext(getActivity());

        FM = getFragmentManager();
        FT = FM.beginTransaction();
        manager = new LinearLayoutManager(activity);

        //Fin

        //Inicialización de elementos

        swipeContainer = (SwipeRefreshLayout) activity.findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeContainer.setOnRefreshListener(this);

        recyclerView = (RecyclerView) activity.findViewById(R.id.rc_view);
        recyclerView.setLayoutManager(manager);

        fab = (FloatingActionButton) activity.findViewById(R.id.fab);
        fab.setOnClickListener(this);

        //Publicidad
        adView = (AdView) activity.findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //Fin

        //Funcionalidad
        daoIndicador = new DAOIndicador(activity.getApplicationContext());
        daoIndicador.setDataDownloadListener(this);
        daoIndicador.obtenerIndicadores();
        //Fin
    }


    //Clic de los elementos
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rc_view:
                break;

        }
    }

    @Override
    public void onRefresh() {
        if (!misc.comprobarConectividad()) {
            Snackbar.make(getActivity().findViewById(R.id.mainView), "Verifica tu conexión a internet", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            if (swipeContainer.isRefreshing())
                swipeContainer.setRefreshing(false);

            FT.remove(FM.findFragmentByTag("Main"));
            FT.add(R.id.mainView, new ConexionFail(), "Failed");
            FT.commit();
        } else {
            List<Indicador> lista_vacia = new ArrayList<Indicador>();
            recyclerView.setAdapter(new AdapterIndicador(lista_vacia, getActivity().getApplicationContext()));
            daoIndicador.obtenerIndicadores();
        }
    }

    //Interfaces, implementaciones
    @Override
    public void dataDownloadedSuccessfully(Object data) {
        List<Indicador> list = (List<Indicador>) data;
        adapter = new AdapterIndicador(list, getActivity());
        recyclerView.setAdapter(adapter);
        swipeContainer.setRefreshing(false);

    }

    @Override
    public void dataDownloadedFailed() {

    }
}
